CREATE TABLE USER_BLOCK_TABLE (

	ID bigserial NOT NULL,
	BLOCKED_USER_ID bigserial NOT NULL,

	PRIMARY KEY (ID, BLOCKED_USER_ID)
);