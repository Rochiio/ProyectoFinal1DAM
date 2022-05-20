-- noinspection SqlWithoutWhereForFile

delete from animeLists;
delete from reviews;
delete from animes;
delete from usuarios;

insert into usuarios values ('f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454',
                             'pepe',date('17/12/2015'),
                             '123',
                             null,
                             'pepe@gmail.com'
                             ,date('17/12/2015')
                             ,false);
insert into animes values ('f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455',
                           'title',
                           'title',
                           'Finished Airing',
                           'Adventure',
                           date('17/12/2015'),
                           'img',
                           5,
                           'rating',
                           'TV');

insert into animeLists values ('f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454',
                               'f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455');

insert into reviews values ('f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454',
                            'f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455',
                            10,
                            'f8c3de3d-1fea-4d7c-a8b0-29f63c4c3456',
                            'ta guapo'
                            );



