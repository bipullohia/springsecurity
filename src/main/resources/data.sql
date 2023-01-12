INSERT INTO users (username, password, enabled)
	values ('user', '{noop}user1', true);
	
INSERT INTO users (username, password, enabled)
	values ('admin', '{noop}admin1', true);
	
INSERT INTO authorities (username, authority)
	values ('user', 'ROLE_USER');
	
INSERT INTO authorities (username, authority)
	values ('admin', 'ROLE_ADMIN');