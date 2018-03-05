INSERT INTO users (user_id, username, password) VALUES
	('1', 'john@example.com', 'password'),
	('2', 'jane@example.com', 'password');

INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope,
authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity,
refresh_token_validity, additional_information, autoapprove) VALUES
	('acme', 'resource', 'secret', 'read,write', 'authorization_code,refresh_token,implicit,password,client_credentials', '', '', 3600, 2592000, '{}', 'true');