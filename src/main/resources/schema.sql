CREATE TABLE IF NOT EXISTS test.tweet
(
    id      int auto_increment
        primary key,
    date    datetime     null,
    message varchar(280) null
);

CREATE TABLE IF NOT EXISTS test.user
(
    id   int auto_increment
        primary key,
    name varchar(255) null
);

CREATE TABLE IF NOT EXISTS test.user_followers
(
    user_id      int not null primary key,
    followers_id int not null primary key,
    constraint UK_4gm8jmbvlrabk1xcoa2rik9fy
        unique (user_id,followers_id),
    constraint FKokc5w6fibhnthvwnxjxyrlfc1
        foreign key (user_id) references test.user (id),
    constraint FKpvkdr9tjpc96kdwe7591oixnj
        foreign key (followers_id) references test.user (id)
);

CREATE TABLE IF NOT EXISTS test.user_tweets
(
    user_id   int not null,
    tweets_id int not null,
    constraint UK_4gm8jmbvlrabk1xcoa2rik9fj
        unique (tweets_id),
    constraint FKjkwjg1veljdlbx3xasigjx46b
        foreign key (tweets_id) references test.tweet (id),
    constraint FKjveis3pshbb4sifbhfjq6958u
        foreign key (user_id) references test.user (id)
);

