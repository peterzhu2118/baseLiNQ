create table basestation (name varchar(255), uniqueid char(10), longitude double(9,6), latitude double(8,6), altitude smallint(4));
create table cabinet (basestationid char(10), slotnumber smallint(4) unsigned);
create table txboard (basestationid char(10), cabinetslotnumber smallint(4) unsigned, slotnumber smallint(4) unsigned, transmitpower tinyint(2), frequency smallint(4) unsigned);
create table antenna (basestationid char(10), slotnumber smallint(4) unsigned, height smallint(4) unsigned, azimuth smallint(3) unsigned, downtilt smallint(3) unsigned);