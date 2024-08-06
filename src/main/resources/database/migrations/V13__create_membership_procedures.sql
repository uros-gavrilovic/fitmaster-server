CREATE OR REPLACE FUNCTION check_active_memberships(p_package_id BIGINT)
    RETURNS VOID AS $$
BEGIN
    IF EXISTS (SELECT 1 FROM membership ms WHERE ms.package_id = p_package_id AND ms.end_date >= CURRENT_DATE)
        THEN RAISE EXCEPTION 'Package with ID % has active memberships. Cannot update.', p_package_id;
    END IF;
END;
$$ LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------

-- CREATE OR REPLACE FUNCTION trigger_check_active_memberships()
--     RETURNS TRIGGER AS $$
-- BEGIN
--     PERFORM check_active_memberships(NEW.id);
--     RETURN NEW; -- Necessary for 'BEFORE' triggers
-- END;
-- $$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION trigger_check_active_memberships()
    RETURNS TRIGGER AS $$
BEGIN
    -- TG_OP is a special variable that holds the operation type (INSERT, UPDATE, DELETE)
    IF TG_OP = 'UPDATE' THEN
        PERFORM check_active_memberships(NEW.id);
    ELSIF TG_OP = 'DELETE' THEN
        PERFORM check_active_memberships(OLD.id);
    END IF;
    RETURN NEW; -- Necessary for 'BEFORE' triggers on UPDATE
END;
$$ LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------------------

DROP TRIGGER IF EXISTS before_package_update_or_delete ON package;
CREATE TRIGGER before_package_update_or_delete
    BEFORE UPDATE OR DELETE ON package
    FOR EACH ROW EXECUTE FUNCTION trigger_check_active_memberships();

------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION update_membership_end_date()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.end_date := NEW.start_date + (SELECT duration FROM package WHERE id = NEW.package_id);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS set_membership_end_date ON membership;
CREATE TRIGGER set_membership_end_date
    BEFORE INSERT OR UPDATE ON membership
    FOR EACH ROW EXECUTE FUNCTION update_membership_end_date();
