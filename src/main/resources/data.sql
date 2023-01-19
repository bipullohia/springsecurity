INSERT INTO users (username, password, enabled)
	values ('user', '{noop}user', true);
	
INSERT INTO users (username, password, enabled)
	values ('admin', '{noop}admin', true);
	
INSERT INTO authorities (username, authority)
	values ('user', 'ROLE_USER');
	
INSERT INTO authorities (username, authority)
	values ('admin', 'ROLE_ADMIN');