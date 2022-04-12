create unique index unique_customer_email on customer (email);

create unique index unique_address on address (customer_id, street, number, complement, zip_code);
