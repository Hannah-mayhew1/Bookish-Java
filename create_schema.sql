CREATE TABLE books (
	isbn varchar(20),
    book_title varchar(500),
    author_first_name varchar(30),
    author_second_name varchar(30),
    category varchar(30),
    total_copies int,
    PRIMARY KEY (isbn)
);

CREATE TABLE members (
	first_name varchar(30),
    second_name varchar(30),
    id int AUTO_INCREMENT,
    PRIMARY KEY (id)
);

CREATE TABLE checkout (
	isbn varchar(20),
    member_id int,
    date_checked_out date,
    date_due_back date,
    checkout_id int AUTO_INCREMENT,
    PRIMARY KEY (checkout_id),
    FOREIGN KEY (member_id) REFERENCES members(id),
    FOREIGN KEY (isbn) REFERENCES books (isbn)
);