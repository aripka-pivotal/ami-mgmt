insert into AmiDetails (id, ipaddress, dnsname) values (100, '192.168.0.1', '192.168.0.1.aws.com');
insert into AmiDetails (id, ipaddress, dnsname) values (101, '192.168.0.2', '192.168.0.2.aws.com');
insert into AmiDetails (id, ipaddress, dnsname) values (102, '192.168.0.3', '192.168.0.3.aws.com');
--insert into AmiDetails (id, ipaddress, dnsname) values (103, '192.168.0.4', '192.168.0.4.aws.com');

insert into Attendee (id, firstname, lastname, address, city, state, zipcode, phonenumber, emailaddress, amiDetails_id) values (5001, 'John', 'Smith', '123 Main St', 'Akron', 'OH', '44321', '330-123-4567', 'jsmith@gopivotal.com', 100);
insert into Attendee (id, firstname, lastname, address, city, state, zipcode, phonenumber, emailaddress, amiDetails_id) values (5002, 'Sally', 'Struthers', '321 Oak St', 'Akron', 'OH', '44321', '614-123-4567', 'sstruthers@gopivotal.com', 101);
insert into Attendee (id, firstname, lastname, address, city, state, zipcode, phonenumber, emailaddress) values (5003, 'Jane', 'Doe', '4444 High St', 'Columbus', 'OH', '43333', '330-123-4567', 'jdoe@gopivotal.com');
insert into Attendee (id, firstname, lastname, emailaddress) values (5004, 'Andrew', 'Rpka', 'air@gopivotal.com');


insert into Session (id, attendee_id, name, date, completed) values (100, 5002, 'Session 1', '2013-12-01', false);
insert into Session (id, attendee_id, name, date, completed) values (101, 5002, 'Session 2', '2013-12-01', true);

update AmiDetails set attendee_id = 5001 where id = 100;
update AmiDetails set attendee_id = 5002 where id = 101;
