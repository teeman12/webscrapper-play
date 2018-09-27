DROP TABLE IF EXISTS sandbox.search_entry;


CREATE TABLE sandbox.search_entry (
  id BIGSERIAL PRIMARY KEY,
  name character varying(256),
  link character varying(500),
  exchange character varying(256),
  key_word character varying(256),
  page_point int,
  is_junk int,
  discovery_date date

  );

