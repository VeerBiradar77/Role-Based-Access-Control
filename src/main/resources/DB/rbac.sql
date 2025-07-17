-- PERMISSIONS
-- ---------------------------------------------
-- 1 = data READ
-- 2 = data WRITE
-- 3 = admin READ
-- 4 = admin WRITE
-- 5 = doctor READ
-- 6 = doctor WRITE
-- 7 = blood donor READ
-- 8 = blood donor WRITE

INSERT INTO permission (id, api_path, access_level, is_active) VALUES (1, '/api/data.*', 'READ', TRUE);
INSERT INTO permission (id, api_path, access_level, is_active) VALUES (2, '/api/data.*', 'WRITE', TRUE);
INSERT INTO permission (id, api_path, access_level, is_active) VALUES (3, '/api/admin.*', 'READ', TRUE);
INSERT INTO permission (id, api_path, access_level, is_active) VALUES (4, '/api/admin.*', 'WRITE', TRUE);
INSERT INTO permission (id, api_path, access_level, is_active) VALUES (5, '/api/doctor.*', 'READ', TRUE);
INSERT INTO permission (id, api_path, access_level, is_active) VALUES (6, '/api/doctor.*', 'WRITE', TRUE);
INSERT INTO permission (id, api_path, access_level, is_active) VALUES (7, '/api/blood-donor.*', 'READ', TRUE);
INSERT INTO permission (id, api_path, access_level, is_active) VALUES (8, '/api/blood-donor.*', 'WRITE', TRUE);

-- ---------------------------------------------
-- ROLES
-- ---------------------------------------------
-- 1 = USER
-- 2 = ADMIN
-- 3 = DOCTOR
-- 4 = BLOOD DONOR

INSERT INTO ROLE (id, NAME, is_active) VALUES (1, 'USER', TRUE);
INSERT INTO ROLE (id, NAME, is_active) VALUES (2, 'ADMIN', TRUE);
INSERT INTO ROLE (id, NAME, is_active) VALUES (3, 'DOCTOR', TRUE);
INSERT INTO ROLE (id, NAME, is_active) VALUES (4, 'BLOOD_DONOR', TRUE);

-- ---------------------------------------------
-- ROLE INHERITANCE
-- ADMIN inherits DOCTOR and BLOOD DONOR permissions
-- ---------------------------------------------
INSERT INTO role_inheritance (role_id, inherited_role_id) VALUES (2, 3); -- ADMIN > DOCTOR
INSERT INTO role_inheritance (role_id, inherited_role_id) VALUES (2, 4); -- ADMIN > BLOOD DONOR

-- ---------------------------------------------
-- ROLE-PERMISSION mappings
-- ---------------------------------------------

-- USER: read/write DATA only
INSERT INTO role_permissions (role_id, permissions_id) VALUES (1, 1);
INSERT INTO role_permissions (role_id, permissions_id) VALUES (1, 2);

-- DOCTOR:
--   - READ/WRITE doctor
--   - READ blood donor
--   - READ data (optional)
INSERT INTO role_permissions (role_id, permissions_id) VALUES (3, 5); -- doctor read
INSERT INTO role_permissions (role_id, permissions_id) VALUES (3, 6); -- doctor write
INSERT INTO role_permissions (role_id, permissions_id) VALUES (3, 7); -- blood donor read

-- BLOOD DONOR:
--   - READ/WRITE blood donor
--   - READ doctor
INSERT INTO role_permissions (role_id, permissions_id) VALUES (4, 7); -- donor read
INSERT INTO role_permissions (role_id, permissions_id) VALUES (4, 8); -- donor write
INSERT INTO role_permissions (role_id, permissions_id) VALUES (4, 5); -- doctor read

-- ADMIN: has all extra direct permissions
INSERT INTO role_permissions (role_id, permissions_id) VALUES (2, 1);
INSERT INTO role_permissions (role_id, permissions_id) VALUES (2, 2);
INSERT INTO role_permissions (role_id, permissions_id) VALUES (2, 3);
INSERT INTO role_permissions (role_id, permissions_id) VALUES (2, 4);
INSERT INTO role_permissions (role_id, permissions_id) VALUES (2, 5);
INSERT INTO role_permissions (role_id, permissions_id) VALUES (2, 6);
INSERT INTO role_permissions (role_id, permissions_id) VALUES (2, 7);
INSERT INTO role_permissions (role_id, permissions_id) VALUES (2, 8);

-- ---------------------------------------------
-- USERS
-- ---------------------------------------------
-- replace password with hashed password
INSERT INTO USER (id, user_name, PASSWORD, is_active, created_at, updated_at)
VALUES (1, 'user', 'user@123', TRUE, NOW(), NOW()); -- USER

INSERT INTO USER (id, user_name, PASSWORD, is_active, created_at, updated_at)
VALUES (2, 'admin', 'admin@123', TRUE, NOW(), NOW()); -- ADMIN

INSERT INTO USER (id, user_name, PASSWORD, is_active, created_at, updated_at)
VALUES (3, 'doctor', 'doctor@123', TRUE, NOW(), NOW()); -- DOCTOR

INSERT INTO USER (id, user_name, PASSWORD, is_active, created_at, updated_at)
VALUES (4, 'donor', 'donor@123', TRUE, NOW(), NOW()); -- BLOOD DONOR

-- ---------------------------------------------
-- ASSIGN ROLES to USERS
-- ---------------------------------------------

INSERT INTO user_roles (user_id, roles_id) VALUES (1, 1); -- veer => USER
INSERT INTO user_roles (user_id, roles_id) VALUES (2, 2); -- pradeep => ADMIN
INSERT INTO user_roles (user_id, roles_id) VALUES (3, 3); -- doc1 => DOCTOR
INSERT INTO user_roles (user_id, roles_id) VALUES (4, 4); -- donor1 => BLOOD DONOR

-- ---------------------------------------------
-- USER GROUPS 
-- ---------------------------------------------

 INSERT INTO user_groups (id, NAME, is_active) VALUES (1, 'MedicalTeam', TRUE);
 INSERT INTO user_user_groups (user_id, user_groups_id) VALUES (3, 1);
 INSERT INTO user_groups_roles (user_group_id, roles_id) VALUES (1, 3);