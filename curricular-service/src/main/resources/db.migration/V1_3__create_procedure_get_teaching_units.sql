CREATE OR REPLACE FUNCTION get_teaching_units(
    page_number INT,
    page_size INT
)
RETURNS TABLE (
    id BIGINT,
    name VARCHAR,
    duration VARCHAR,
    purpose VARCHAR,
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
    c1.id, c1.name, c1.duration, c1.purpose, c1.status, c1.created_at, c1.updated_at
FROM
    teaching_unit_projections c1
        JOIN (
        SELECT c2.id
        FROM teaching_unit_projections c2
        ORDER BY c2.name
            LIMIT page_size OFFSET the_offset
    ) AS c3 ON c1.id = c3.id;
END;
$$;
