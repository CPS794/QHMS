mysql -u root -p
CREATE DATABASE QHMSDB;
CREATE USER qhmsmanager IDENTIFIED BY 'dbmanage';
GRANT ALL PRIVILEGES ON QHMSDB.* TO 'qhmsmanager' WITH GRANT OPTION;

USE qhmsdb;

DROP TABLE IF EXISTS evaluate;
DROP TABLE IF EXISTS answer;
DROP TABLE IF EXISTS problem;
DROP TABLE IF EXISTS user;
CREATE TABLE user (
	id CHAR(10),
	name VARCHAR(50) NOT NULL,
	password CHAR(50) NOT NULL,
	studentClass VARCHAR(50),
	team INT,
	type INT, # 0=student, 1=admin
	PRIMARY KEY (id, type)
);

INSERT INTO `qhmsdb`.`user` (`id`, `name`, `password`, `studentClass`, `type`) VALUES ('000', '000-student', 'c4ca4238a0b923820dcc509a6f75849b', '000', '0');	
INSERT INTO `qhmsdb`.`user` (`id`, `name`, `password`, `studentClass`, `type`) VALUES ('000', '000-admin', 'c4ca4238a0b923820dcc509a6f75849b', '000', '1');	
INSERT INTO `qhmsdb`.`user` (`id`, `name`, `password`, `studentClass`, `type`) VALUES ('panjing', '000-admin', '06b2d6dcc2f71f7c38aae8e1fd6e1569', '000', '1');	

DROP TABLE IF EXISTS system_variable;
CREATE TABLE system_variable (
	name VARCHAR(50) PRIMARY KEY,
	chinese_name VARCHAR(500) NOT NULL,
	value VARCHAR(50)
);

INSERT INTO `qhmsdb`.`system_variable` (`name`, `chinese_name`, `value`) VALUES ('CourseName', '课程名称', '');
INSERT INTO `qhmsdb`.`system_variable` (`name`, `chinese_name`, `value`) VALUES ('ChapterTotal', '章节数', '');
INSERT INTO `qhmsdb`.`system_variable` (`name`, `chinese_name`, `value`) VALUES ('Running', '是否正在运行', '');
INSERT INTO `qhmsdb`.`system_variable` (`name`, `chinese_name`, `value`) VALUES ('Team_MultiChapter', '是否允许一个队伍负责多个章节', '');
INSERT INTO `qhmsdb`.`system_variable` (`name`, `chinese_name`, `value`) VALUES ('Team_CapabilityMinimum', '队伍人数下限', '');
INSERT INTO `qhmsdb`.`system_variable` (`name`, `chinese_name`, `value`) VALUES ('Team_CapabilityMaximum', '队伍人数上限', '');
INSERT INTO `qhmsdb`.`system_variable` (`name`, `chinese_name`, `value`) VALUES ('Homework_DurationMinimum', '作业有效时间段下限', '');
INSERT INTO `qhmsdb`.`system_variable` (`name`, `chinese_name`, `value`) VALUES ('Homework_DurationMaximum', '作业有效时间段上限', '');
INSERT INTO `qhmsdb`.`system_variable` (`name`, `chinese_name`, `value`) VALUES ('Homework_QuestionMinimum', '作业题目数量下限', '');
INSERT INTO `qhmsdb`.`system_variable` (`name`, `chinese_name`, `value`) VALUES ('Homework_QuestionMaximum', '作业题目数量上限', '');
INSERT INTO `qhmsdb`.`system_variable` (`name`, `chinese_name`, `value`) VALUES ('Homework_TimeLimit', '作业是否限时完成', '');
INSERT INTO `qhmsdb`.`system_variable` (`name`, `chinese_name`, `value`) VALUES ('Homework_TimeLimitMinimum', '作业最短时限', '');
INSERT INTO `qhmsdb`.`system_variable` (`name`, `chinese_name`, `value`) VALUES ('Homework_TimeLimitMaximum', '作业最长时限', '');
INSERT INTO `qhmsdb`.`system_variable` (`name`, `chinese_name`, `value`) VALUES ('Homework_RandamQuestion', '是否随机出题', '');
INSERT INTO `qhmsdb`.`system_variable` (`name`, `chinese_name`, `value`) VALUES ('Homework_AllowRedo', '是否允许重做', '');

UPDATE `qhmsdb`.`system_variable` SET `value`='8' WHERE `name`='ChapterTotal';


DROP TABLE IF EXISTS evaluate;
DROP TABLE IF EXISTS score_percentage;
CREATE TABLE score_percentage (
	id INT AUTO_INCREMENT PRIMARY KEY,
	type INT, # 0=to student by teacher, 1=to teacher by student, 10=to student by admin, 11=to teacher by admin
	name VARCHAR(50) NOT NULL,
	percentage DOUBLE,
	used INT NOT NULL # 0=not used 1=student evaluate 2=admin evaluate
);

INSERT INTO `qhmsdb`.`score_percentage` (`type`, `name`, `percentage`, `used`) VALUES ('0', '习题质量', '0.20', '1');
INSERT INTO `qhmsdb`.`score_percentage` (`type`, `name`, `percentage`, `used`) VALUES ('0', '实践题质量', '0.20', '1');
INSERT INTO `qhmsdb`.`score_percentage` (`type`, `name`, `percentage`, `used`) VALUES ('0', '指导', '0.20', '1');
INSERT INTO `qhmsdb`.`score_percentage` (`type`, `name`, `percentage`, `used`) VALUES ('0', '章节总结', '0.20', '1');
INSERT INTO `qhmsdb`.`score_percentage` (`type`, `name`, `percentage`, `used`) VALUES ('0', '态度公平', '0.20', '1');


DROP TABLE IF EXISTS problem;
DROP TABLE IF EXISTS evaluate;
DROP TABLE IF EXISTS team;
DROP TABLE IF EXISTS chapter;
CREATE TABLE chapter (
	id INT PRIMARY KEY,
	chinese_id VARCHAR(50)  NOT NULL,
	name VARCHAR(50) NOT NULL,
	status INT
);

INSERT INTO `qhmsdb`.`chapter` (`id`, `chinese_id`, `name`) VALUES ('1', '第一章', '绪论');
INSERT INTO `qhmsdb`.`chapter` (`id`, `chinese_id`, `name`) VALUES ('2', '第二章', '线性表');
INSERT INTO `qhmsdb`.`chapter` (`id`, `chinese_id`, `name`) VALUES ('3', '第三章', '栈和队列');
INSERT INTO `qhmsdb`.`chapter` (`id`, `chinese_id`, `name`) VALUES ('4', '第四章', '数组');
INSERT INTO `qhmsdb`.`chapter` (`id`, `chinese_id`, `name`) VALUES ('5', '第五章', '树');
INSERT INTO `qhmsdb`.`chapter` (`id`, `chinese_id`, `name`) VALUES ('6', '第六章', '图论');
INSERT INTO `qhmsdb`.`chapter` (`id`, `chinese_id`, `name`) VALUES ('7', '第七章', '查找');
INSERT INTO `qhmsdb`.`chapter` (`id`, `chinese_id`, `name`) VALUES ('8', '第八章', '排序');

UPDATE `qhmsdb`.`chapter` SET `status`='0' WHERE `id`='1';
UPDATE `qhmsdb`.`chapter` SET `status`='0' WHERE `id`='2';
UPDATE `qhmsdb`.`chapter` SET `status`='0' WHERE `id`='3';
UPDATE `qhmsdb`.`chapter` SET `status`='0' WHERE `id`='4';
UPDATE `qhmsdb`.`chapter` SET `status`='0' WHERE `id`='5';
UPDATE `qhmsdb`.`chapter` SET `status`='0' WHERE `id`='6';
UPDATE `qhmsdb`.`chapter` SET `status`='0' WHERE `id`='7';
UPDATE `qhmsdb`.`chapter` SET `status`='0' WHERE `id`='8';


DROP TABLE IF EXISTS team;
CREATE TABLE team (
	id INT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	chapter_id INT,
	FOREIGN KEY (chapter_id) REFERENCES chapter(id)
);

INSERT INTO `qhmsdb`.`team` (`id`, `name`, `chapter_id`) VALUES ('2', '第二组', '2');
INSERT INTO `qhmsdb`.`team` (`id`, `name`, `chapter_id`) VALUES ('3', '第三组', '3');
INSERT INTO `qhmsdb`.`team` (`id`, `name`, `chapter_id`) VALUES ('5', '第五组', '5');
INSERT INTO `qhmsdb`.`team` (`id`, `name`, `chapter_id`) VALUES ('6', '第六组', '6');
INSERT INTO `qhmsdb`.`team` (`id`, `name`, `chapter_id`) VALUES ('7', '第七组', '7');
INSERT INTO `qhmsdb`.`team` (`id`, `name`, `chapter_id`) VALUES ('8', '第八组', '8');

DROP VIEW IF EXISTS grade_view;
DROP TABLE IF EXISTS answer;
DROP TABLE IF EXISTS problem;
DROP TABLE IF EXISTS problem_type;
CREATE TABLE problem_type (
	id INT PRIMARY KEY,
	chinese_name VARCHAR(50) NOT NULL,
	default_point INT NOT NULL,
	judge INT NOT NULL # 0 = automatically, 1 = manually
);

INSERT INTO `qhmsdb`.`problem_type` (`id`, `chinese_name`, `default_point`, `judge`) VALUES ('1', '判断题', '1', '0');
INSERT INTO `qhmsdb`.`problem_type` (`id`, `chinese_name`, `default_point`, `judge`) VALUES ('2', '单项选择题', '2', '0');
INSERT INTO `qhmsdb`.`problem_type` (`id`, `chinese_name`, `default_point`, `judge`) VALUES ('3', '不定项选择题', '3', '0');
INSERT INTO `qhmsdb`.`problem_type` (`id`, `chinese_name`, `default_point`, `judge`) VALUES ('4', '填空题', '4', '1');
INSERT INTO `qhmsdb`.`problem_type` (`id`, `chinese_name`, `default_point`, `judge`) VALUES ('5', '简答题', '5', '1');
INSERT INTO `qhmsdb`.`problem_type` (`id`, `chinese_name`, `default_point`, `judge`) VALUES ('6', '综合题', '10', '1');


DROP VIEW IF EXISTS grade_view;
DROP TABLE IF EXISTS answer;
DROP TABLE IF EXISTS problem;
CREATE TABLE problem (
	id INT AUTO_INCREMENT PRIMARY KEY,
	type_id INT NOT NULL,
	chapter_id INT,
	description TEXT NOT NULL,
	given_option TEXT,
	answer TEXT NOT NULL,
	default_point INT NOT NULL,
	author_id CHAR(10) NOT NULL,
	FOREIGN KEY (author_id) REFERENCES user(id),
	FOREIGN KEY (chapter_id) REFERENCES chapter(id),
	FOREIGN KEY (type_id) REFERENCES problem_type(id) 
);

DROP VIEW IF EXISTS grade_view;
DROP TABLE IF EXISTS answer;
CREATE TABLE answer (
	student_id CHAR(10) NOT NULL,
	problem_id INT NOT NULL,
	answer TEXT,
	point INT,
	teacher_id CHAR(10),
	PRIMARY KEY (student_id, problem_id),
	FOREIGN KEY (student_id) REFERENCES user(id),
	FOREIGN KEY (teacher_id) REFERENCES user(id),
	FOREIGN KEY (problem_id) REFERENCES problem(id)
);

DROP TABLE IF EXISTS evaluate;
CREATE TABLE evaluate (
	student_id CHAR(10) NOT NULL,
	chapter_id INT NOT NULL,
	score_percentage_id INT NOT NULL,
	point INT,
	PRIMARY KEY (student_id, chapter_id, score_percentage_id),
	FOREIGN KEY (student_id) REFERENCES user(id),
	FOREIGN KEY (chapter_id) REFERENCES chapter(id),
	FOREIGN KEY (score_percentage_id) REFERENCES score_percentage(id)
);

DROP VIEW IF EXISTS grade_view;
CREATE VIEW grade_view AS
	SELECT user.id AS student_id, chapter.id AS chapter_id, SUM(answer.point) AS chapter_grade 
	FROM user, chapter, answer, problem 
	WHERE problem.chapter_id=chapter.id AND answer.problem_id=problem.id AND user.id=answer.student_id AND user.type=0 
	GROUP BY user.id,chapter.id
;

DROP VIEW IF EXISTS evaluate_view_chapter;
CREATE VIEW evaluate_view_chapter AS
	SELECT evaluate.chapter_id AS chapter, score_percentage.name AS iteam, score_percentage.percentage AS weight, AVG(evaluate.point) AS grade 
	FROM evaluate, score_percentage
	WHERE score_percentage.id = evaluate.score_percentage_id 
	GROUP BY evaluate.chapter_id, score_percentage.id
;

DROP VIEW IF EXISTS evaluate_view_person;
CREATE VIEW evaluate_view_person AS
	SELECT user.id, user.name, evaluate_view_chapter.chapter, SUM(weight*grade) AS total_grade 
	FROM evaluate_view_chapter, user 
	WHERE user.team = evaluate_view_chapter.chapter AND user.type = 0
	GROUP BY user.id
;

CREATE VIEW final_grade AS
	SELECT grade_view.student_id, user.name,
		SUM(CASE WHEN chapter_id=2 then chapter_grade ELSE 0 END) AS Chapter_2, 
		SUM(CASE WHEN chapter_id=3 then chapter_grade ELSE 0 END) AS Chapter_3, 
		SUM(CASE WHEN chapter_id=5 then chapter_grade ELSE 0 END) AS Chapter_5, 
		SUM(CASE WHEN chapter_id=6 then chapter_grade ELSE 0 END) AS Chapter_6, 
		SUM(CASE WHEN chapter_id=7 then chapter_grade ELSE 0 END) AS Chapter_7, 
		SUM(chapter_grade)/SUM(CASE WHEN chapter_id=user.team then 0 ELSE 1 END) AS Average 
	FROM qhmsdb.grade_view,qhmsdb.user 
	WHERE user.id=grade_view.student_id AND user.type=0 
	GROUP BY grade_view.student_id
;
