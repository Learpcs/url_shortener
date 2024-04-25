-- DROP TABLE IF EXISTS user_dao CASCADE;
-- DROP TABLE IF EXISTS url_dao CASCADE;

INSERT INTO user_dao (id, login, password) VALUES (0, 'test', 'test') ON CONFLICT DO NOTHING;
INSERT INTO url_dao (id, url, owner_id) VALUES (0, 'https://vk.com', 0) ON CONFLICT DO NOTHING;
;