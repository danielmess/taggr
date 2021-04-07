-- **************************************************************
-- This script destroys the database and associated users
-- **************************************************************

-- The following line terminates any active connections to the database so that it can be destroyed
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'taggr_final';

DROP DATABASE taggr_final;

DROP USER final_taggr_owner;
DROP USER final_taggr_appuser;
DROP USER final_capstone_owner;
DROP USER final_capstone_appuser;