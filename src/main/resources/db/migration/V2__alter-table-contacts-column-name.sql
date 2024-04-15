ALTER TABLE contacts ADD CONSTRAINT unique_name UNIQUE (name);
ALTER TABLE contacts ADD CONSTRAINT unique_phone UNIQUE (phone);