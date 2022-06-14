USE climbing_platform;

INSERT INTO `climbing_platform`.`user_information` (`user_index`, `user_name`, `email`, `phone`, `nickname`, `device_token`, `join_date`) VALUES (NULL, "장재원", "admin@naver.com", "010-0000-0000", "관리자", "wd15AX215...", NOW());
INSERT INTO `climbing_platform`.`user_information` (`user_index`, `user_name`, `email`, `phone`, `nickname`, `device_token`, `join_date`) VALUES (NULL, "임준호", "testmail@naver.com", "010-3302-7492", "귀요미준호", "fqV68xox...", NOW());
INSERT INTO `climbing_platform`.`user_information` (`user_index`, `user_name`, `email`, `phone`, `nickname`, `device_token`, `join_date`) VALUES (NULL, "양희웅", "test22@naver.com", "010-1234-5678", "밥셔틀웅이", "AAAAe5Y...", NOW());
INSERT INTO `climbing_platform`.`user_information` (`user_index`, `user_name`, `email`, `phone`, `nickname`, `device_token`, `join_date`) VALUES (NULL, "장재원", "tmail455@naver.com", "010-3215-9512", "젠장재원", "sz9WSPm...", NOW());

INSERT INTO `climbing_platform`.`user_permit` (`user_index`, `email_permit`, `sms_permit`, `push_permit`) VALUES (1, 0, 0, 0);
INSERT INTO `climbing_platform`.`user_permit` (`user_index`, `email_permit`, `sms_permit`, `push_permit`) VALUES (2, 1, 1, 1);
INSERT INTO `climbing_platform`.`user_permit` (`user_index`, `email_permit`, `sms_permit`, `push_permit`) VALUES (3, 1, 0, 1);
INSERT INTO `climbing_platform`.`user_permit` (`user_index`, `email_permit`, `sms_permit`, `push_permit`) VALUES (4, 0, 0, 1);

INSERT INTO `climbing_platform`.`user_account` (`user_index`, `user_id`, `password`) VALUES (1, "admin", "admin123");
INSERT INTO `climbing_platform`.`user_account` (`user_index`, `user_id`, `password`) VALUES (2, "test123", "v9sdf5...");
INSERT INTO `climbing_platform`.`user_account` (`user_index`, `user_id`, `password`) VALUES (3, "ttest321" , "testpwd");
INSERT INTO `climbing_platform`.`user_account` (`user_index`, `user_id`, `password`) VALUES (4, "testusr99", "r21frr3...");

INSERT INTO `climbing_platform`.`outdoor_location` (`location_index`, `address_province`, `address_city`, `address_detail`, `latitude`, `longitude`, `name`, `description`, `isImage`, `score`) VALUES (NULL, "경상북도", "대구", "북구 819", "35.964967", "128.613613", "연경도약대", "desc1", 0, 3.5);
INSERT INTO `climbing_platform`.`outdoor_location` (`location_index`, `address_province`, `address_city`, `address_detail`, `latitude`, `longitude`, `name`, `description`, `isImage`, `score`) VALUES (NULL, "경기도", "의왕", "오전동 모락산", "37.369541", "126.981090", "모락산", "desc2", 0, 4.0);
INSERT INTO `climbing_platform`.`outdoor_location` (`location_index`, `address_province`, `address_city`, `address_detail`, `latitude`, `longitude`, `name`, `description`, `isImage`, `score`) VALUES (NULL, "강원도", "원주", "지정면 간현리 1108-16", "37.368743", "127.823278", "간현 암벽", "desc3", 0, 4.5);

INSERT INTO `climbing_platform`.`outdoor_route` (`route_index`, `location_index`, `route_name`, `bolt_count`, `difficulty`, `isImage`, `like_count`) VALUES (NULL, 1, "무명길", 6, "5.10a", 0, 2);
INSERT INTO `climbing_platform`.`outdoor_route` (`route_index`, `location_index`, `route_name`, `bolt_count`, `difficulty`, `isImage`, `like_count`) VALUES (NULL, 1, "홀로서기", 5, "5.10a", 0, 0);
INSERT INTO `climbing_platform`.`outdoor_route` (`route_index`, `location_index`, `route_name`, `bolt_count`, `difficulty`, `isImage`, `like_count`) VALUES (NULL, 1, "선미길~산으로", 4, "5.10a", 0, 1);

INSERT INTO `climbing_platform`.`outdoor_route_likes` (`idx`, `route_index`, `nickname`, `like_time`) VALUES (NULL, 1, "밥셔틀웅이", NOW());
INSERT INTO `climbing_platform`.`outdoor_route_likes` (`idx`, `route_index`, `nickname`, `like_time`) VALUES (NULL, 1, "귀요미준호", NOW());
INSERT INTO `climbing_platform`.`outdoor_route_likes` (`idx`, `route_index`, `nickname`, `like_time`) VALUES (NULL, 1, "젠장재원", NOW());

INSERT INTO `climbing_platform`.`outdoor_score` (`idx`, `user_index`, `location_index`, `score`) VALUES (NULL, 2, 1, 3.5);
INSERT INTO `climbing_platform`.`outdoor_score` (`idx`, `user_index`, `location_index`, `score`) VALUES (NULL, 3, 2, 4.0);
INSERT INTO `climbing_platform`.`outdoor_score` (`idx`, `user_index`, `location_index`, `score`) VALUES (NULL, 4, 3, 4.5);

INSERT INTO `climbing_platform`.`user_clearlist` (`idx`, `user_index`, `route_index`, `clear_date`) VALUES (NULL, 2, 1, NOW());
INSERT INTO `climbing_platform`.`user_clearlist` (`idx`, `user_index`, `route_index`, `clear_date`) VALUES (NULL, 3, 1, NOW());
INSERT INTO `climbing_platform`.`user_clearlist` (`idx`, `user_index`, `route_index`, `clear_date`) VALUES (NULL, 4, 1, NOW());

INSERT INTO `climbing_platform`.`post_list` (`post_index`, `header`, `body`, `post_date`, `nickname`, `modify_time`, `isImage`, `comment_count`, `like_count`) VALUES (NULL, "제목1", "내용1", NOW(), "귀요미준호", NULL, 0, 5, 2);
INSERT INTO `climbing_platform`.`post_list` (`post_index`, `header`, `body`, `post_date`, `nickname`, `modify_time`, `isImage`, `comment_count`, `like_count`) VALUES (NULL, "제목2", "내용2", NOW(), "밥셔틀웅이", NULL, 0, 1, 0);
INSERT INTO `climbing_platform`.`post_list` (`post_index`, `header`, `body`, `post_date`, `nickname`, `modify_time`, `isImage`, `comment_count`, `like_count`) VALUES (NULL, "제목3", "내용3", NOW(), "젠장재원", NOW(), 0, 0, 1);

INSERT INTO `climbing_platform`.`post_comments` (`comment_index`, `post_index`, `nickname`, `comment`, `record_time`, `is_modified`) VALUES (NULL, 1, "귀요미준호", "댓글1", NOW(), 0);
INSERT INTO `climbing_platform`.`post_comments` (`comment_index`, `post_index`, `nickname`, `comment`, `record_time`, `is_modified`) VALUES (NULL, 1, "밥셔틀웅이", "댓글2", NOW(), 0);
INSERT INTO `climbing_platform`.`post_comments` (`comment_index`, `post_index`, `nickname`, `comment`, `record_time`, `is_modified`) VALUES (NULL, 2, "젠장재원", "댓글1", NOW(), 1);

INSERT INTO `climbing_platform`.`post_reply` (`idx`, `comment_index`, `post_index`, `nickname`, `reply`, `record_time`, `is_modified`) VALUES (NULL, 1, 1, "귀요미준호", "답글1", NOW(), 0);
INSERT INTO `climbing_platform`.`post_reply` (`idx`, `comment_index`, `post_index`, `nickname`, `reply`, `record_time`, `is_modified`) VALUES (NULL, 2, 1,"밥셔틀웅이", "답글2-1", NOW(), 0);
INSERT INTO `climbing_platform`.`post_reply` (`idx`, `comment_index`, `post_index`, `nickname`, `reply`, `record_time`, `is_modified`) VALUES (NULL, 2, 1,"젠장재원", "답글2-2", NOW(), 1);

INSERT INTO `climbing_platform`.`post_likes` (`idx`, `post_index`, `nickname`, `like_time`) VALUES (NULL, 1, "귀요미준호", NOW());
INSERT INTO `climbing_platform`.`post_likes` (`idx`, `post_index`, `nickname`, `like_time`) VALUES (NULL, 1, "밥셔틀웅이", NOW());
INSERT INTO `climbing_platform`.`post_likes` (`idx`, `post_index`, `nickname`, `like_time`) VALUES (NULL, 3, "젠장재원", NOW());

INSERT INTO `climbing_platform`.`notice_list` (`notice_index`, `header`, `body`, `post_date`, `modify_date`, `isImage`) VALUES (NULL, "제목1", "내용1", NOW(), NULL, 0);
INSERT INTO `climbing_platform`.`notice_list` (`notice_index`, `header`, `body`, `post_date`, `modify_date`, `isImage`) VALUES (NULL, "제목2", "내용2", NOW(), NULL, 0);
INSERT INTO `climbing_platform`.`notice_list` (`notice_index`, `header`, `body`, `post_date`, `modify_date`, `isImage`) VALUES (NULL, "제목3", "내용3", NOW(), NOW(), 0);

INSERT INTO `climbing_platform`.`outdoor_comments` (`idx`, `route_index`, `nickname`, `comment`, `record_time`) VALUES (NULL, 1, "귀요미준호", "넘모 쉬웡~", NOW());
INSERT INTO `climbing_platform`.`outdoor_comments` (`idx`, `route_index`, `nickname`, `comment`, `record_time`) VALUES (NULL, 1, "밥셔틀웅이", "밥하기 시러~", NOW());
INSERT INTO `climbing_platform`.`outdoor_comments` (`idx`, `route_index`, `nickname`, `comment`, `record_time`) VALUES (NULL, 1, "젠장재원", "지엔장~", NOW());