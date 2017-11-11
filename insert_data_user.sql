INSERT INTO sec_permission (id, permission_label, permission_value) VALUES 
('listcust', 'Tampilan List Customer', 'MENU_LIST_CUSTOMER'),
('formcust', 'Tampilan Form Customer', 'MENU_FORM_CUSTOMER'),
('delcust', 'Delete Customer', 'ACT_DEL_CUSTOMER'),
('listtrx', 'Tampilan List Transaksi', 'MENU_LIST_TRANSAKSI'),
('formtrx', 'Tampilan Form Transaksi', 'MENU_FORM_TRANSAKSI'),
('deltrx', 'Delete Transaksi', 'ACT_DEL_TRANSAKSI');

INSERT INTO sec_role (id, role_name, role_desc) VALUES
('adm', 'ADMINISTRATOR', 'Admin Aplikasi'),
('opr', 'OPERATOR', 'Operator Aplikasi');

INSERT INTO sec_role_permission (id_role, id_permission) VALUES
('adm', 'listcust'),
('adm', 'formcust'),
('adm', 'delcust'),
('adm', 'listtrx'),
('adm', 'formtrx'),
('adm', 'deltrx'),
('opr', 'listcust'),
('opr', 'formcust'),
('opr', 'listtrx'),
('opr', 'formtrx');

INSERT INTO sec_user (id, username, fullname, password, is_active, id_role) VALUES
('admin', 'admin', 'administrator', 'admin123', true, 'adm'),
('operator', 'operator', 'operator', 'operator123', true, 'opr');
