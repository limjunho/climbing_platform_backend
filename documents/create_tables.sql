CREATE DATABASE climbing_platform;

CREATE TABLE IF NOT EXISTS `climbing_platform`.`user_information` (
  `user_index` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(255) NOT NULL,
  `nickname` VARCHAR(255) NOT NULL,
  `device_token` VARCHAR(255) NOT NULL,
  `join_date` DATE NOT NULL,
  PRIMARY KEY (`user_index`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC) VISIBLE,
  UNIQUE INDEX `device_token_UNIQUE` (`device_token` ASC) VISIBLE,
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `climbing_platform`.`post_list` (
  `post_index` INT NOT NULL AUTO_INCREMENT,
  `header` VARCHAR(255) NOT NULL,
  `body` VARCHAR(5000) NOT NULL,
  `post_date` DATETIME NOT NULL,
  `nickname` VARCHAR(255) NOT NULL,
  `modify_time` DATETIME NULL,
  `isImage` SMALLINT NOT NULL,
  `comment_count` SMALLINT NOT NULL,
  `like_count` SMALLINT NOT NULL,
  PRIMARY KEY (`post_index`),
  CONSTRAINT `fk_post_list_user_information1`
    FOREIGN KEY (`nickname`)
    REFERENCES `climbing_platform`.`user_information` (`nickname`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `climbing_platform`.`post_image` (
  `image_index` INT NOT NULL AUTO_INCREMENT,
  `post_index` INT NOT NULL,
  `origin_name` VARCHAR(255) NULL,
  `size` VARCHAR(255) NULL,
  `path` VARCHAR(255) NULL,
  PRIMARY KEY (`image_index`),
  INDEX `fk_table1_post_list1_idx` (`post_index` ASC) VISIBLE,
  CONSTRAINT `fk_table1_post_list1`
    FOREIGN KEY (`post_index`)
    REFERENCES `climbing_platform`.`post_list` (`post_index`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `climbing_platform`.`notice_list` (
  `notice_index` INT NOT NULL AUTO_INCREMENT,
  `header` VARCHAR(255) NOT NULL,
  `body` VARCHAR(5000) NOT NULL,
  `post_date` DATETIME NOT NULL,
  `modify_date` DATETIME NULL,
  `isImage` SMALLINT NOT NULL,
  PRIMARY KEY (`notice_index`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `climbing_platform`.`notice_image` (
  `image_index` INT NOT NULL AUTO_INCREMENT,
  `notice_index` INT NOT NULL,
  `origin_name` VARCHAR(255) NULL,
  `size` VARCHAR(255) NULL,
  `path` VARCHAR(255) NULL,
  PRIMARY KEY (`image_index`),
  INDEX `fk_notice_image_notice_list1_idx` (`notice_index` ASC) VISIBLE,
  CONSTRAINT `fk_notice_image_notice_list1`
    FOREIGN KEY (`notice_index`)
    REFERENCES `climbing_platform`.`notice_list` (`notice_index`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `climbing_platform`.`outdoor_location` (
  `location_index` INT NOT NULL AUTO_INCREMENT,
  `address_province` VARCHAR(255) NOT NULL,
  `address_city` VARCHAR(255) NOT NULL,
  `address_detail` VARCHAR(255) NOT NULL,
  `latitude` VARCHAR(255) NOT NULL,
  `longitude` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(5000) NOT NULL,
  `isImage` SMALLINT NOT NULL,
  `score` FLOAT NOT NULL,
  PRIMARY KEY (`location_index`),
  UNIQUE INDEX `address_detail_UNIQUE` (`address_detail` ASC) VISIBLE,
  UNIQUE INDEX `latitude_UNIQUE` (`latitude` ASC) VISIBLE,
  UNIQUE INDEX `longitude_UNIQUE` (`longitude` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `climbing_platform`.`location_image` (
  `image_index` INT NOT NULL AUTO_INCREMENT,
  `location_index` INT NOT NULL,
  `origin_name` VARCHAR(255) NULL,
  `size` VARCHAR(255) NULL,
  `path` VARCHAR(255) NULL,
  PRIMARY KEY (`image_index`),
  INDEX `fk_location_image_outdoor_location1_idx` (`location_index` ASC) VISIBLE,
  CONSTRAINT `fk_location_image_outdoor_location1`
    FOREIGN KEY (`location_index`)
    REFERENCES `climbing_platform`.`outdoor_location` (`location_index`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `climbing_platform`.`outdoor_score` (
  `idx` INT NOT NULL AUTO_INCREMENT,
  `user_index` INT NOT NULL,
  `location_index` INT NOT NULL,
  `score` FLOAT NOT NULL,
  PRIMARY KEY (`idx`),
  INDEX `fk_outdoor_score_user_information1_idx` (`user_index` ASC) VISIBLE,
  INDEX `fk_outdoor_score_outdoor_location1_idx` (`location_index` ASC) VISIBLE,
  CONSTRAINT `fk_outdoor_score_user_information1`
    FOREIGN KEY (`user_index`)
    REFERENCES `climbing_platform`.`user_information` (`user_index`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_outdoor_score_outdoor_location1`
    FOREIGN KEY (`location_index`)
    REFERENCES `climbing_platform`.`outdoor_location` (`location_index`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `climbing_platform`.`outdoor_route` (
  `route_index` INT NOT NULL AUTO_INCREMENT,
  `location_index` INT NOT NULL,
  `route_name` VARCHAR(255) NOT NULL,
  `bolt_count` TINYINT NOT NULL,
  `difficulty` VARCHAR(255) NOT NULL,
  `isImage` SMALLINT NOT NULL,
  `like_count` SMALLINT NOT NULL,
  PRIMARY KEY (`route_index`),
  INDEX `fk_outdoor_route_outdoor_location1_idx` (`location_index` ASC) VISIBLE,
  CONSTRAINT `fk_outdoor_route_outdoor_location1`
    FOREIGN KEY (`location_index`)
    REFERENCES `climbing_platform`.`outdoor_location` (`location_index`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `climbing_platform`.`route_image` (
  `image_index` INT NOT NULL AUTO_INCREMENT,
  `route_index` INT NOT NULL,
  `origin_name` VARCHAR(255) NULL,
  `size` VARCHAR(255) NULL,
  `path` VARCHAR(255) NULL,
  PRIMARY KEY (`image_index`),
  INDEX `fk_route_image_outdoor_route1_idx` (`route_index` ASC) VISIBLE,
  CONSTRAINT `fk_route_image_outdoor_route1`
    FOREIGN KEY (`route_index`)
    REFERENCES `climbing_platform`.`outdoor_route` (`route_index`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `climbing_platform`.`outdoor_route_likes` (
  `idx` INT NOT NULL AUTO_INCREMENT,
  `route_index` INT NOT NULL,
  `nickname` VARCHAR(255) NOT NULL,
  `like_time` DATETIME NOT NULL,
  PRIMARY KEY (`idx`),
  INDEX `fk_outdoor_route_likes_outdoor_route1_idx` (`route_index` ASC) VISIBLE,
  INDEX `fk_outdoor_route_likes_user_information1_idx` (`nickname` ASC) VISIBLE,
  CONSTRAINT `fk_outdoor_route_likes_outdoor_route1`
    FOREIGN KEY (`route_index`)
    REFERENCES `climbing_platform`.`outdoor_route` (`route_index`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_outdoor_route_likes_user_information1`
    FOREIGN KEY (`nickname`)
    REFERENCES `climbing_platform`.`user_information` (`nickname`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `climbing_platform`.`user_account` (
  `user_index` INT NOT NULL,
  `user_id` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`user_index`),
  CONSTRAINT `fk_user_account_user_information1`
    FOREIGN KEY (`user_index`)
    REFERENCES `climbing_platform`.`user_information` (`user_index`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `climbing_platform`.`user_clearlist` (
  `idx` INT NOT NULL AUTO_INCREMENT,
  `user_index` INT NOT NULL,
  `route_index` INT NOT NULL,
  `clear_date` DATE NOT NULL,
  PRIMARY KEY (`idx`),
  INDEX `fk_user_clearlist_user_information1_idx` (`user_index` ASC) VISIBLE,
  INDEX `fk_user_clearlist_outdoor_route1_idx` (`route_index` ASC) VISIBLE,
  CONSTRAINT `fk_user_clearlist_user_information1`
    FOREIGN KEY (`user_index`)
    REFERENCES `climbing_platform`.`user_information` (`user_index`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_clearlist_outdoor_route1`
    FOREIGN KEY (`route_index`)
    REFERENCES `climbing_platform`.`outdoor_route` (`route_index`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `climbing_platform`.`user_permit` (
  `user_index` INT NOT NULL,
  `email_permit` TINYINT NOT NULL,
  `sms_permit` TINYINT NOT NULL,
  `push_permit` TINYINT NOT NULL,
  PRIMARY KEY (`user_index`),
  CONSTRAINT `fk_user_permit_user_information`
    FOREIGN KEY (`user_index`)
    REFERENCES `climbing_platform`.`user_information` (`user_index`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `climbing_platform`.`post_comments` (
  `comment_index` INT NOT NULL AUTO_INCREMENT,
  `post_index` INT NOT NULL,
  `nickname` VARCHAR(255) NOT NULL,
  `comment` VARCHAR(1000) NOT NULL,
  `record_time` DATETIME NOT NULL,
  `is_modified` TINYINT NOT NULL,
  PRIMARY KEY (`comment_index`),
  INDEX `fk_post_comments_post_list1_idx` (`post_index` ASC) VISIBLE,
  CONSTRAINT `fk_post_comments_post_list1`
    FOREIGN KEY (`post_index`)
    REFERENCES `climbing_platform`.`post_list` (`post_index`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_comments_user_information1`
    FOREIGN KEY (`nickname`)
    REFERENCES `climbing_platform`.`user_information` (`nickname`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `climbing_platform`.`post_reply` (
  `idx` INT NOT NULL AUTO_INCREMENT,
  `comment_index` INT NOT NULL,
  `post_index` INT NOT NULL,
  `nickname` VARCHAR(255) NOT NULL,
  `reply` VARCHAR(1000) NOT NULL,
  `record_time` DATETIME NOT NULL,
  `is_modified` TINYINT NOT NULL,
  PRIMARY KEY (`idx`),
  INDEX `fk_post_reply_post_comments1_idx` (`comment_index` ASC) VISIBLE,
  INDEX `fk_post_reply_post_list1_idx` (`post_index` ASC) VISIBLE,
  CONSTRAINT `fk_post_reply_post_comments1`
    FOREIGN KEY (`comment_index`)
    REFERENCES `climbing_platform`.`post_comments` (`comment_index`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_reply_user_information1`
    FOREIGN KEY (`nickname`)
    REFERENCES `climbing_platform`.`user_information` (`nickname`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_post_reply_post_list1`
    FOREIGN KEY (`post_index`)
    REFERENCES `climbing_platform`.`post_list` (`post_index`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `climbing_platform`.`post_likes` (
  `idx` INT NOT NULL AUTO_INCREMENT,
  `post_index` INT NOT NULL,
  `nickname` VARCHAR(255) NOT NULL,
  `like_time` DATETIME NOT NULL,
  PRIMARY KEY (`idx`),
  INDEX `fk_post_likes_post_list1_idx` (`post_index` ASC) VISIBLE,
  INDEX `fk_post_likes_user_information1_idx` (`nickname` ASC) VISIBLE,
  CONSTRAINT `fk_post_likes_post_list1`
    FOREIGN KEY (`post_index`)
    REFERENCES `climbing_platform`.`post_list` (`post_index`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_likes_user_information1`
    FOREIGN KEY (`nickname`)
    REFERENCES `climbing_platform`.`user_information` (`nickname`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `climbing_platform`.`outdoor_comments` (
  `idx` INT NOT NULL AUTO_INCREMENT,
  `route_index` INT NOT NULL,
  `nickname` VARCHAR(255) NOT NULL,
  `comment` VARCHAR(1000) NOT NULL,
  `record_time` DATETIME NOT NULL,
  PRIMARY KEY (`idx`),
  INDEX `fk_outdoor_comments_user_information1_idx` (`nickname` ASC) VISIBLE,
  INDEX `fk_outdoor_comments_outdoor_route1_idx` (`route_index` ASC) VISIBLE,
  CONSTRAINT `fk_outdoor_comments_user_information1`
    FOREIGN KEY (`nickname`)
    REFERENCES `climbing_platform`.`user_information` (`nickname`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_outdoor_comments_outdoor_route1`
    FOREIGN KEY (`route_index`)
    REFERENCES `climbing_platform`.`outdoor_route` (`route_index`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE climbing_platform;

SHOW TABLES;