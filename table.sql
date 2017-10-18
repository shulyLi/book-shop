CREATE TABLE IF NOT EXISTS `book_order` (
  `id`          INT(11)     NOT NULL AUTO_INCREMENT,
  `user_id`     INT(11)     NOT NULL
  COMMENT '用户Id',
  `order_no`    BIGINT      NOT NULL
  COMMENT '订单号',
  `state`       TINYINT(2)  NOT NULL
  COMMENT '订单状态',
  `pay_type`    TINYINT(2)  NOT NULL
  COMMENT '支付类型',
  `pay_fee`     INT(11)     NOT NULL
  COMMENT '支付的钱,分单位',
  `phone`       VARCHAR(13) NOT NULL
  COMMENT '手机号',
  `address`     VARCHAR(50) NOT NULL
  COMMENT '用户地址',
  `detail`      TEXT        NOT NULL
  COMMENT '购买东西的json详情非常详细的信息',
  `create_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE u_idx_order_no(`order_no`),
  KEY idx_user_id(`user_id`)
)
  COMMENT '订单';


CREATE TABLE IF NOT EXISTS `book_order_history` (
  `id`          INT(11)     NOT NULL AUTO_INCREMENT
  COMMENT '这个其实没啥用, 但是　innodb你不创建　他自己也会　隐式的创建',
  `order_no`    BIGINT      NOT NULL
  COMMENT '订单号',
  `state`       TINYINT(2)  NOT NULL
  COMMENT '订单变成了这个状态',
  `message`     VARCHAR(64) NOT NULL
  COMMENT '原因',
  `operator`    TINYINT(2)  NOT NULL
  COMMENT '操作者',
  `create_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY idx_order_no(`order_no`)
)
  COMMENT '订单履历';


CREATE TABLE IF NOT EXISTS `book_user` (
  `id`          INT(11)      NOT NULL AUTO_INCREMENT,
  `email`       VARCHAR(64)  NOT NULL
  COMMENT '邮箱且账户',
  `pass_word`   VARCHAR(64)  NOT NULL
  COMMENT '密码',
  `user_name`   VARCHAR(8)   NOT NULL
  COMMENT '用户名字',
  `user_head`   VARCHAR(128) NOT NULL
  COMMENT '头像',
  `user_mark`   VARCHAR(64)  NOT NULL
  COMMENT '保留字段,　用户脾气',
  `user_type`   INT(11)      NOT NULL
  COMMENT '用户类型, 也可以说是权限',
  `is_del`      TINYINT(1)   NOT NULL
  COMMENT '逻辑删除（用户删除）',
  `is_black`    TINYINT(1)   NOT NULL
  COMMENT '拉黑(系统or我们删除)',
  `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE u_idx_email(`email`)
)
  COMMENT '用户';

CREATE TABLE IF NOT EXISTS `book_good` (
  `id`          INT(11)      NOT NULL AUTO_INCREMENT,
  `book_name`   VARCHAR(24)  NOT NULL
  COMMENT '书名字',
  `book_author` VARCHAR(24)  NOT NULL
  COMMENT '作者',
  `book_head`   VARCHAR(128) NOT NULL
  COMMENT '书的截图',
  `price`       INT(11)      NOT NULL
  COMMENT '售价（分）',
  `sell_cnt`    INT(11)      NOT NULL
  COMMENT '销售数量',
  `simple_desc` VARCHAR(64)  NOT NULL
  COMMENT '简要评价',
  `tag`         VARCHAR(512) NOT NULL
  COMMENT '标签, 逗号分割',
  `is_black`    TINYINT(1)   NOT NULL
  COMMENT '拉黑(系统or我们删除)',
  `stock`       INT(11)      NOT NULL
  COMMENT '库存',
  `beter_part`  VARCHAR(256) NOT NULL
  COMMENT '精彩章节',
  `index`       TEXT         NOT NULL
  COMMENT '目录',
  `detail`      TEXT         NOT NULL
  COMMENT '介绍',
  `update_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间',
  `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY idx_update_time(`update_time`)
)
  COMMENT '书';


CREATE TABLE IF NOT EXISTS `book_comment` (
  `id`          INT(11)       NOT NULL AUTO_INCREMENT,
  `user_id`     INT(11)       NOT NULL
  COMMENT '用户Id',
  `order_no`    BIGINT        NOT NULL
  COMMENT '订单号',
  `book_id`     INT(11)       NOT NULL
  COMMENT '书Id',
  `comment`     VARCHAR(1024) NOT NULL
  COMMENT '评价内容',
  `replay`      VARCHAR(2014) NOT NULL
  COMMENT '回复',
  `is_del`      TINYINT(1)    NOT NULL
  COMMENT '逻辑删除（用户删除）',
  `is_black`    TINYINT(1)    NOT NULL
  COMMENT '拉黑，防止和谐词汇',
  `replay_time` DATETIME      NOT NULL
  COMMENT '回复时间',
  `create_time` DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY u_idx_order_no(`order_no`),
  KEY idx_book(`book_id`),
  KEY idx_user(`user_id`)
)
  COMMENT '评价';