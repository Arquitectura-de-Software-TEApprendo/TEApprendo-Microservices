CREATE OR REPLACE FUNCTION get_learning_sessions(
    page_number INT,
    page_size INT
)
RETURNS TABLE (
    id BIGINT,
    duration VARCHAR,
    beginning VARCHAR,
    development VARCHAR,
    conclusion VARCHAR,
    status VARCHAR,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
)
LANGUAGE plpgsql
AS $$
DECLARE
the_offset INT;
BEGIN
    -- Calcular el offset
    the_offset := (page_size * page_number) - page_size;

    -- Devolver los resultados directamente
RETURN QUERY
SELECT
    c1.id, c1.duration, c1.beginning, c1.development, c1.conclusion, c1.status, c1.created_at, c1.updated_at
FROM
    learning_session_projections c1
        JOIN (
        SELECT c2.id
        FROM learning_session_projections c2
        ORDER BY c2.created_at DESC
            LIMIT page_size OFFSET the_offset
    ) AS c3 ON c1.id = c3.id;
END;
$$;
