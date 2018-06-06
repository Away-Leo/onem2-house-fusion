/*地址类型*/
INSERT INTO ess_codecategory (deleted, raw_version, category_cd, category_nm, description, is_common, is_enabled, is_tree) VALUES ( 0,0, 't093', '地址类型', '地址类型', 0, 0, 1);
INSERT INTO ess_simplecode (is_tree,deleted, raw_version, code, description, name, disp_order, full_code, is_default, is_fixed, category_cd) VALUES (1,0,0, 't0930', '家庭地址',  '家庭地址', 0, 't0930', 1,1, 't093');
INSERT INTO ess_simplecode (is_tree,deleted, raw_version, code, description, name, disp_order, full_code, is_default, is_fixed, category_cd) VALUES (1,0,0, 't0931', '户籍地址',  '户籍地址', 0, 't0931', 1,1, 't093');

/*审核选项*/
INSERT INTO ess_codecategory (deleted, raw_version, category_cd, category_nm, description, is_common, is_enabled, is_tree) VALUES ( 0,0, 'identifyType', '审核选项', '审核选项', 0, 0, 1);
INSERT INTO ess_simplecode (is_tree,deleted, raw_version, code, description, name, disp_order, full_code, is_default, is_fixed, category_cd) VALUES (1,0,0, '1', '同意后成为专家',  '同意', 0, '1', 1,1, 'identifyType');
INSERT INTO ess_simplecode (is_tree,deleted, raw_version, code, description, name, disp_order, full_code, is_default, is_fixed, category_cd) VALUES (1,0,0, '2', '新注册用户为待选',  '待选', 0, '2', 1,1, 'identifyType');
INSERT INTO ess_simplecode (is_tree,deleted, raw_version, code, description, name, disp_order, full_code, is_default, is_fixed, category_cd) VALUES (1,0,0, '3', '注销',  '注销', 0, '3', 1,1, 'identifyType');