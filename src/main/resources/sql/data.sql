-- Insert data into captain table
insert into captain (id, birth_date, name)
values
    (gen_random_uuid(), '1970-01-01', 'Captain A'),
    (gen_random_uuid(), '1980-02-02', 'Captain B');

-- Insert data into ship table
insert into ship (id, vessel_number, captain_id)
values
    (gen_random_uuid(), 'Vessel123', (select id from captain where name = 'Captain A')),
    (gen_random_uuid(), 'Vessel456', (select id from captain where name = 'Captain B'));

-- Insert data into purchase_order table
insert into purchase_order (id, order_number, ship_id)
values
    (gen_random_uuid(), 'PO123', (select id from ship where vessel_number = 'Vessel123')),
    (gen_random_uuid(), 'PO456', (select id from ship where vessel_number = 'Vessel456'));

-- Insert data into dock_operation table
insert into dock_operation (id, ship_arrival_time, ship_departure_time, vessel_number, purchase_order_id, ship_id)
values
    (gen_random_uuid(), '2023-10-01T10:00:00', '2023-10-02T10:00:00', 'Vessel123', (select id from purchase_order where order_number = 'PO123'), (select id from ship where vessel_number = 'Vessel123')),
    (gen_random_uuid(), '2023-10-03T10:00:00', '2023-10-04T10:00:00', 'Vessel456', (select id from purchase_order where order_number = 'PO456'), (select id from ship where vessel_number = 'Vessel456'));

insert into inspection_operation (is_done, is_open, inspection_time, dock_operation_id, id, inspection_number)
values
    (true, false, '2023-10-01T12:00:00', (select id from dock_operation where vessel_number = 'Vessel123'), gen_random_uuid(), 'INSP123'),
    (false, true, '2023-10-03T12:00:00', (select id from dock_operation where vessel_number = 'Vessel456'), gen_random_uuid(), 'INSP456');

-- Insert data into bunker_operation table
insert into bunker_operation (operation_date,id, ship_id,vessel_number)
values
    ('2024-10-01',gen_random_uuid(), (select id from ship where vessel_number = 'Vessel123'),  'Vessel123'),
    ('2024-10-10',gen_random_uuid(), (select id from ship where vessel_number = 'Vessel456'), 'Vessel456');


-- Insert data into loading_operation table
insert into loading_operation (quantity, loading_time, id, ship_id, cargo_type)
values
    (1000.0, '2023-10-01T14:00:00', gen_random_uuid(), (select id from ship where vessel_number = 'Vessel123'), 'Oil'),
    (2000.0, '2023-10-03T14:00:00', gen_random_uuid(), (select id from ship where vessel_number = 'Vessel456'), 'Coal');