CREATE TABLE `customer_details` (
  `customer_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `phone_number` VARCHAR(20) NULL,
  `email` VARCHAR(100) NULL,
  `dob` DATE NULL,
  PRIMARY KEY (`customer_id`)
);