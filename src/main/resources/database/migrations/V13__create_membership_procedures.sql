CREATE OR REPLACE FUNCTION check_active_memberships(p_package_id BIGINT)
    RETURNS VOID AS $$
BEGIN
    IF EXISTS (SELECT 1 FROM membership ms WHERE ms.package_id = p_package_id AND ms.end_date >= CURRENT_DATE)
        THEN RAISE EXCEPTION 'Package with ID % has active memberships. Cannot update.', p_package_id;
    END IF;
END;
$$ LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION trigger_check_active_memberships()
    RETURNS TRIGGER AS $$
BEGIN
    PERFORM check_active_memberships(NEW.id);
    RETURN NEW; -- Necessary for 'BEFORE' triggers
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER before_package_update
    BEFORE UPDATE ON package
    FOR EACH ROW
EXECUTE FUNCTION trigger_check_active_memberships();
