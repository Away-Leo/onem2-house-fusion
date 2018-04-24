/*地址类型*/
INSERT INTO ess_codecategory (deleted, raw_version, category_cd, category_nm, description, is_common, is_enabled, is_tree) VALUES ( 0,0, 't093', '地址类型', '地址类型', 0, 0, 1);
INSERT INTO ess_simplecode (is_tree,deleted, raw_version, code, description, name, disp_order, full_code, is_default, is_fixed, category_cd) VALUES (1,0,0, 't0930', '家庭地址',  '家庭地址', 0, 't0930', 1,1, 't093');
INSERT INTO ess_simplecode (is_tree,deleted, raw_version, code, description, name, disp_order, full_code, is_default, is_fixed, category_cd) VALUES (1,0,0, 't0931', '户籍地址',  '户籍地址', 0, 't0931', 1,1, 't093');