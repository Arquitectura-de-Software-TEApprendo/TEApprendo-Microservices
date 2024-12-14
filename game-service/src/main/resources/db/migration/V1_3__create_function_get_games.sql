CREATE OR REPLACE FUNCTION get_games(
    page_number INT,
    page_size INT
)
RETURNS TABLE (
    id BIGINT,
    name VARCHAR,
    "type" VARCHAR,
    difficulty VARCHAR,
    topic VARCHAR,
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
    c1.id, c1.name, c1.type, c1.difficulty, c1.topic, c1.status, c1.created_at, c1.updated_at
FROM
    game_projections c1
        JOIN (
        SELECT c2.id
        FROM game_projections c2
        ORDER BY c2.created_at DESC
            LIMIT page_size OFFSET the_offset
    ) AS c3 ON c1.id = c3.id;
END;
$$;
