create table if not exists urls (
    identifier   varchar(16) PRIMARY KEY,
    original_url varchar(2000) not null, -- De facto URL limit
    visits       int       not null default 0,
    created_at   timestamp with time zone default now()
);

CREATE OR REPLACE FUNCTION delete_old_urls()
    RETURNS TRIGGER
    LANGUAGE PLPGSQL
AS $$
BEGIN
    delete from urls where CURRENT_DATE >= created_at + INTERVAL '6 months';
    return;
END;
$$;

DROP TRIGGER if exists on_new_url on urls;
CREATE TRIGGER on_new_url
    AFTER INSERT
    ON urls
    FOR EACH STATEMENT
    EXECUTE PROCEDURE delete_old_urls();