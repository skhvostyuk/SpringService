INSERT IGNORE INTO lecture_halls VALUES (1, 'First Hall', 30, false, '09:00', '18:00', 1000);
INSERT IGNORE INTO lecture_halls VALUES (2, 'Second Hall', 40, true, '10:00', '18:00', 1500);
INSERT IGNORE INTO lecture_halls VALUES (3, '3-d Hall', 1000, true, '09:00', '20:00', 10000);
INSERT IGNORE INTO lecture_halls VALUES (4, '4-th Hall', 15, false, '12:00', '18:00', 1000);
INSERT IGNORE INTO lecture_halls VALUES (5, '5-th Hall', 45, false, '15:00', '19:00', 2000);

INSERT IGNORE INTO lectures VALUES (1, 'qqq', 'qqq lecturer', 20, '2019-03-28 13:00', 120, 1000, 1);
INSERT IGNORE INTO lectures VALUES (2, 'www lecture', 'www lecturer', 25, '2019-03-29 16:00', 120, 2000, 1);
INSERT IGNORE INTO lectures VALUES (3, 'eee lecture', 'eee lecturer', 30, '2019-03-29 13:00', 120, 1500, 2);
INSERT IGNORE INTO lectures VALUES (4, 'rrr lecture', 'rrr lecturer', 900, '2019-03-30 13:00', 120, 10000, 3);

INSERT IGNORE INTO visitors VALUES (1, 'qqq visitor', 1);
INSERT IGNORE INTO visitors VALUES (2, 'www visitor', 1);
INSERT IGNORE INTO visitors VALUES (3, 'eee visitor', 2);
INSERT IGNORE INTO visitors VALUES (4, 'rrr visitor', 2);
INSERT IGNORE INTO visitors VALUES (5, 'ttt visitor', 3);
