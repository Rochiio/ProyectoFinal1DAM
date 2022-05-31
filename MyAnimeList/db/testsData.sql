delete
from animeLists;
delete
from reviews;
delete
from animes;
delete
from usuarios;

insert into usuarios
values ('f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454',
        'pepe',
        '2015-12-17 00:00:00',
        '123',
        null,
        'pepe@gmail.com',
        '2015-12-17 00:00:00',
        false);

insert into usuarios
values ('f8c3de3d-1fea-4d7c-a8b0-29f63c4c3444',
        'pepedelete',
        '2015-12-17 00:00:00',
        '123',
        null,
        'pepe@gmail.com',
        '2015-12-17 00:00:00',
        false);

insert into usuarios
values ('f8c3de3d-1fea-4d7c-a8b0-29f63c4c3434',
        'pepeupdate',
        '2015-12-17 00:00:00',
        '123',
        null,
        'pepe@gmail.com',
        '2015-12-17 00:00:00',
        false);

insert into animes
values ('f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455',
        'title',
        'title',
        'Finished Airing',
        'Adventure',
        '2015-12-17 00:00:00',
        'img',
        5,
        'rating',
        'TV');

insert into animes
values ('f8c3de3d-1fea-4d7c-a8b0-29f63c4c3456',
        'titleupdate',
        'title',
        'Finished Airing',
        'Adventure',
        '2015-12-17 00:00:00',
        'img',
        5,
        'rating',
        'TV');

insert into animes
values ('f8c3de3d-1fea-4d7c-a8b0-29f63c4c3457',
        'titledelete',
        'title',
        'Finished Airing',
        'Adventure',
        '2015-12-17 00:00:00',
        'img',
        5,
        'rating',
        'TV');

insert into reviews
values ('f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454',
        'f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455',
        10,
        'f8c3de3d-1fea-4d7c-a8b0-29f63c4c3456',
        'ta guapo');

insert into animeLists
values ('f8c3de3d-1fea-4d7c-a8b0-29f63c4c3434',
        'f8c3de3d-1fea-4d7c-a8b0-29f63c4c3456');



