Create database if not exists bookCollection;

use bookCollection;

drop table if exists books;
drop table if exists series;
drop table if exists author;

create table author (
	authorId int(10) not null auto_increment,
	firstName varchar(50),
	lastName varchar(50) not null,
	primary key(authorId)
	);
create table series (
	seriesId int(10) not null auto_increment,
	seriesName varchar(50) not null,
	primary key(seriesId)
	);

create table books (
        bookId int(10) not null auto_increment,
        title varchar(50) not null,     
        authorId int(10) not null,
	seriesId int(10),
	primary key(bookId),
	foreign key(authorId) references author(authorId),
	foreign key(seriesId) references series(seriesId)
        );

insert into author (firstName, lastName) values ("Rick", "Rioridan");
insert into author (firstName, lastName) values ("Jennifer A.", "Nielsen");

insert into series (seriesName) values ("The Kane Chronicles");
insert into books (title, authorId, seriesId) values ("The Red Pyramid", 1, 1);
insert into books (title, authorId, seriesId) values ("The Throne of Fire", 1, 1);
insert into books (title, authorId, seriesId) values ("The Serpent's Shadow",1, 1);

insert into series (seriesName) values ("The Heroes of Olympus");
insert into books (title, authorId, seriesId) values ("The Lost Hero", 1, 2);
insert into books (title, authorId, seriesId) values ("The Son of Neptune", 1, 2);
insert into books (title, authorId, seriesId) values ("The Mark of Athena", 1, 2);
insert into books (title, authorId, seriesId) values ("The House of Hades", 1, 2);
insert into books (title, authorId, seriesId) values ("The Blood of Olympus", 1, 2);

insert into series (seriesName) values ("Ascendance");
insert into books (title, authorId, seriesId) values ("The False Prince", 2, 3);
insert into books (title, authorId, seriesId) values ("The Runaway King", 2, 3);
insert into books (title, authorId, seriesId) values ("The Shadow Throne", 2, 3);
insert into books (title, authorId, seriesId) values ("The Captive Kingdom", 2, 3);

insert into series (seriesName) values ("Traitor's Game");
insert into books (title, authorId, seriesId) values ("The Traitor's Game", 2, 4);
insert into books (title, authorId, seriesId) values ("The Deceiver's Heart", 2, 4);
insert into books (title, authorId, seriesId) values ("The Warrior's Curse", 2, 4);

insert into series (seriesName) values ("Mark of the Theif");
insert into books (title, authorId, seriesId) values ("Mark of the Thief", 2, 5);
insert into books (title, authorId, seriesId) values ("Rise of the Wolf", 2, 5);
insert into books (title, authorId, seriesId) values ("Wrath of the Storm", 2, 5);

insert into series (seriesName) values ("Percy Jackson and the Olympians");
insert into books (title, authorId, seriesId) values ("The Lightning Thief", 1, 6);
insert into books (title, authorId, seriesId) values ("The Sea of Monsters", 1, 6);
insert into books (title, authorId, seriesId) values ("The Titan's Curse", 1, 6);
insert into books (title, authorId, seriesId) values ("The Battle of the Labyrinth", 1, 6);
insert into books (title, authorId, seriesId) values ("The Last Olympian", 1, 6);