--Part 1
  /*`id` int NOT NULL,
  `employer` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `skills` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)*/

--Part 2
select name from employer where location = "St. Louis City";
--Part 3
drop table job;
--Part 4
SELECT * FROM skill inner join job_skills on skill.id = job_skills.skills_id where job_skills.jobs_id is not null order by name asc;