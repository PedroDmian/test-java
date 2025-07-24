INSERT INTO loans (customer_id, identity, loan_date, amount, status) VALUES
 ('00103228', 1, '2021-01-10', 37500.00, 'Pendiente'),
 ('00103228', 2, '2021-01-19', 725.18, 'Pendiente'),
 ('00103228', 3, '2021-01-31', 1578.22, 'Pendiente'),
 ('00103228', 4, '2021-02-04', 380.00, 'Pendiente'),
 ('70099925', 1, '2021-01-07', 2175.25, 'Pagado'),
 ('70099925', 2, '2021-01-13', 499.99, 'Pagado'),
 ('70099925', 3, '2021-01-24', 5725.18, 'Pendiente'),
 ('70099925', 4, '2021-02-07', 876.13, 'Pendiente'),
 ('00298185', 1, '2021-02-04', 545.55, 'Pendiente'),
 ('15000125', 1, '2020-12-31', 15220.00, 'Pagado');

INSERT INTO debit_accounts (customer_id, balance, status) VALUES
('00103228', 15375.28, 'Activa'),
('70099925', 3728.51, 'Bloqueada'),
('00298185', 0.00, 'Cancelada'),
('15000125', 235.28, 'Activa');

SELECT * FROM loans ORDER BY loans.loan_date asc