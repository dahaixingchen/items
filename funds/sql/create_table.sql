-- 基金基础表
CREATE TABLE `funds_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `funds_id` varchar(15) NOT NULL COMMENT '基金编号',
  `name` varchar(200) NOT NULL COMMENT '基金名称',
  `type` int(11) DEFAULT NULL COMMENT '基金类型',
  `BFUNDTYPE` int(11) DEFAULT NULL,
  `FEATURE` varchar(200) DEFAULT NULL,
  `price` decimal(10,4) DEFAULT NULL COMMENT '净值',
  `HLDWJZ` varchar(200) DEFAULT NULL,
  `price_all` decimal(10,4) DEFAULT NULL COMMENT '累计净值',
  `FTYI` varchar(200) DEFAULT NULL,
  `TEYI` varchar(200) DEFAULT NULL,
  `TFYI` varchar(200) DEFAULT NULL,
  `price_daily_percent` decimal(10,4) DEFAULT NULL COMMENT '日涨幅',
  `price_weekly_percent` decimal(10,4) DEFAULT NULL COMMENT '近1周涨幅',
  `price_monthly_percent` decimal(10,4) DEFAULT NULL COMMENT '近1月涨幅',
  `price_3month_percent` decimal(10,4) DEFAULT NULL COMMENT '近3月涨幅',
  `price_6month_percent` decimal(10,4) DEFAULT NULL COMMENT '近6月涨幅',
  `price_year_percent` decimal(10,4) DEFAULT NULL COMMENT '近1年涨幅',
  `price_2year_percent` decimal(10,4) DEFAULT NULL COMMENT '近2年涨幅',
  `price_3year_percent` decimal(10,4) DEFAULT NULL COMMENT '近3年涨幅',
  `price_5year_percent` decimal(10,4) DEFAULT NULL COMMENT '近5年涨幅',
  `price_current_year_percent` decimal(10,4) DEFAULT NULL COMMENT '今年以来涨幅',
  `price_all_percent` decimal(10,4) DEFAULT NULL COMMENT '成立以来涨幅',
  `ZJL` varchar(200) DEFAULT NULL,
  `TARGETYIELD` varchar(200) DEFAULT NULL,
  `CYCLE` varchar(200) DEFAULT NULL,
  `KFR` varchar(200) DEFAULT NULL,
  `LEVERAGE` varchar(200) DEFAULT NULL,
  `BAGTYPE` varchar(200) DEFAULT NULL,
  `BUY` varchar(200) DEFAULT NULL,
  `LISTTEXCH` varchar(200) DEFAULT NULL,
  `NEWTEXCH` varchar(200) DEFAULT NULL,
  `ISLISTTRADE` varchar(200) DEFAULT NULL,
  `common_earnings_1year` decimal(10,4) DEFAULT NULL COMMENT '普通定投1年收益率',
  `common_earnings_2year` decimal(10,4) DEFAULT NULL COMMENT '普通定投2年收益率',
  `common_earnings_3year` decimal(10,4) DEFAULT NULL COMMENT '普通定投3年收益率',
  `common_earnings_5year` decimal(10,4) DEFAULT NULL COMMENT '普通定投5年收益率',
  `smart_earnings_1year` decimal(10,4) DEFAULT NULL COMMENT '智慧定投1年收益率',
  `smart_earnings_2year` decimal(10,4) DEFAULT NULL COMMENT '智慧定投2年收益率',
  `smart_earnings_3year` decimal(10,4) DEFAULT NULL COMMENT '智慧定投3年收益率',
  `smart_earnings_5year` decimal(10,4) DEFAULT NULL COMMENT '智慧定投5年收益率',
  `target_earnings_1year` decimal(10,4) DEFAULT NULL COMMENT '目标止盈1年收益率',
  `target_earnings_2year` decimal(10,4) DEFAULT NULL COMMENT '目标止盈2年收益率',
  `target_earnings_3year` decimal(10,4) DEFAULT NULL COMMENT '目标止盈3年收益率',
  `target_earnings_5year` decimal(10,4) DEFAULT NULL COMMENT '目标止盈5年收益率',
  `move_earnings_1year` decimal(10,4) DEFAULT NULL COMMENT '移动止盈1年收益率',
  `move_earnings_2year` decimal(10,4) DEFAULT NULL COMMENT '移动止盈2年收益率',
  `move_earnings_3year` decimal(10,4) DEFAULT NULL COMMENT '移动止盈3年收益率',
  `move_earnings_5year` decimal(10,4) DEFAULT NULL COMMENT '移动止盈5年收益率',
  `total_assets` decimal(20,4) DEFAULT NULL COMMENT '总资产',
  `SALEVOLUME` varchar(50) DEFAULT NULL,
  `PV_Y` varchar(50) DEFAULT NULL,
  `DTCOUNT_Y` varchar(50) DEFAULT NULL,
  `ORGSALESRANK` varchar(200) DEFAULT NULL,
  `ISABNORMAL` varchar(50) DEFAULT NULL,
  `data_date` varchar(50) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `finds_id_and_date` (`funds_id`,`data_date`)
) ENGINE=InnoDB AUTO_INCREMENT=34457 DEFAULT CHARSET=utf8 COMMENT='基金';
alter table my_funds modify column total_assets decimal(20,4) DEFAULT NULL COMMENT '总资产'
alter table funds_info add UNIQUE KEY `finds_id_and_date` (`funds_id`,`data_date`)

-- 基金实时估算表
CREATE TABLE `forecast` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `funds_id` varchar(15) NOT NULL COMMENT '基金编号',
  `name` varchar(200) NOT NULL COMMENT '基金名称',
  `forecast_price` decimal(10,4) NOT NULL COMMENT '预估净值涨跌',
  `forecast_percent` decimal(10,4) NOT NULL COMMENT '预估涨跌百分比',
  `ISBUY` varchar(10) DEFAULT NULL COMMENT '是否可以购买',
  `LISTTEXCH` varchar(200) DEFAULT NULL,
  `ISLISTTRADE` varchar(200) DEFAULT NULL,
  `data_date` varchar(50) DEFAULT NULL COMMENT '数据时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `funds_id` (`funds_id`,`data_date`)
) ENGINE=InnoDB AUTO_INCREMENT=59420 DEFAULT CHARSET=utf8 COMMENT='基金预估涨幅表';

-- 基金持仓的股票表
CREATE TABLE `stock_holdings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `funds_id` varchar(15) NOT NULL COMMENT '基金代码id',
  `stock_id` varchar(15) NOT NULL COMMENT '股票代码id',
  `name` varchar(200) DEFAULT NULL COMMENT '持股名称',
  `holdings_percent` decimal(10,4) DEFAULT NULL COMMENT '持仓占比',
  `TEXCH` varchar(200) DEFAULT NULL,
  `ISINVISBL` varchar(200) DEFAULT NULL,
  `PCTNVCHGTYPE` varchar(200) DEFAULT NULL COMMENT '较上期的占比情况',
  `holdings_old_percent` decimal(10,4) DEFAULT NULL COMMENT '对比上期具体占比数值',
  `NEWTEXCH` varchar(200) DEFAULT NULL,
  `INDEXCODE` varchar(200) DEFAULT NULL,
  `industry` varchar(200) DEFAULT NULL COMMENT '具体行业',
  `data_date` varchar(20) DEFAULT NULL COMMENT '基金公布的数据的时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `finds_id_and_date` (`funds_id`,`stock_id`,`data_date`)
) ENGINE=InnoDB AUTO_INCREMENT=125077 DEFAULT CHARSET=utf8 COMMENT='基金投资各个公司股票的占比';

-- 基金所在的行业表
CREATE TABLE `industry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `funds_id` varchar(15) NOT NULL COMMENT '基金id',
  `name` varchar(200) DEFAULT NULL COMMENT '行业名称',
  `price` decimal(10,4) DEFAULT NULL COMMENT '本基金在这个行业投资的金额',
  `price_percent` decimal(10,4) DEFAULT NULL COMMENT '本行业在本基金中的占比',
  `data_date` varchar(20) DEFAULT NULL COMMENT '基金公布的数据的时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `finds_id_and_date` (`funds_id`,`name`,`data_date`)
) ENGINE=InnoDB AUTO_INCREMENT=66832 DEFAULT CHARSET=utf8 COMMENT='基金投资各个行业的占比';

-- 持有基金
create table my_funds(
  id int not null auto_increment,
	funds_id varchar(15) not null comment'基金id',
	name varchar(200) not null comment'基金名称',
	platform varchar(20) not null default 'zhifubao' comment'购买平台',
	earnings_percent decimal(10,4) not null comment'今日为止的盈利情况',
	price_buying decimal(10,4) not null comment'买入价格',
	price_selling decimal(10,4) comment'卖出价格',
	is_hold boolean not null default 1 comment'是否持有',
	update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	primary key (id),
	unique key funds_key (funds_id,create_time)
)comment'持有基金'
alter table my_funds modify column funds_id varchar(15) not null comment'基金id'

create table funds_history(
	id BIGINT NOT NULL AUTO_INCREMENT,
	funds_id varchar(15) NOT NULL COMMENT '基金编号',
  name varchar(200) NOT NULL COMMENT '基金名称',
	price decimal(10,4) not null comment'单位净值',
	price_all decimal(10,4) not null comment'累计净值',
	price_daily_percent decimal(10,4) not null comment'当日涨幅',
	price_up_down int not null DEFAULT 1 comment'涨跌情况，1:涨,-1:跌',
	NAVTYPE varchar(10) ,
	RATE varchar(50) ,
	MUI varchar(50) ,
	SYI varchar(50) ,
	data_date varchar(50) DEFAULT NULL,
  create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id) USING BTREE,
  UNIQUE KEY `finds_id_and_date` (funds_id,data_date) USING BTREE
)comment'基金的历史净值'

CREATE TABLE `industry_layout` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `industry_code` varchar(20) NOT NULL COMMENT '行业代码',
  `industry_name` varchar(50) NOT NULL COMMENT '行业名称',
  `price_daily_percent` decimal(10,4) DEFAULT NULL COMMENT '行业日涨幅',
  `price_weekly_percent` decimal(10,4) DEFAULT NULL COMMENT '行业近1周涨幅',
  `price_monthly_percent` decimal(10,4) DEFAULT NULL COMMENT '近1月涨幅',
  `price_3month_percent` decimal(10,4) DEFAULT NULL COMMENT '近3月涨幅',
  `price_year_percent` decimal(10,4) DEFAULT NULL COMMENT '近1年涨幅',
  `price_current_year_percent` decimal(10,4) DEFAULT NULL COMMENT '今年以来涨幅',
  `industry_price` decimal(10,4) DEFAULT NULL COMMENT '行业指数',
  `industry_3min_speed` decimal(10,4) DEFAULT NULL COMMENT '行业3分钟涨速',
  `industry_5min_speed` decimal(10,4) DEFAULT NULL COMMENT '行业5分钟涨速',
  `industry_fund_weekly` decimal(20,0) DEFAULT NULL COMMENT '本周行业资金流向',
  `industry_fund_daily` decimal(20,0) DEFAULT NULL COMMENT '当日行业资金流向',
  `TYPE` varchar(50) DEFAULT NULL,
  `LZTS` varchar(50) DEFAULT NULL,
  `LDTS` varchar(50) DEFAULT NULL,
  `JLCTS` varchar(50) DEFAULT NULL,
  `IndexCode` varchar(50) DEFAULT NULL,
  `JLRTS` varchar(50) DEFAULT NULL,
  `funds_price_weekly_percent` decimal(10,4) DEFAULT NULL COMMENT '行业推荐基金近1周涨幅',
  `funds_price_monthly_percent` decimal(10,4) DEFAULT NULL COMMENT '行业推荐基金近1月涨幅',
  `funds_price_3month_percent` decimal(10,4) DEFAULT NULL COMMENT '行业推荐基金近3月涨幅',
  `funds_price_year_percent` decimal(10,4) DEFAULT NULL COMMENT '行业推荐基金近1年涨幅',
  `funds_price_current_year_percent` decimal(10,4) DEFAULT NULL COMMENT '行业推荐基金今年以来涨幅',
  `funds_id` varchar(15)  COMMENT '行业推荐基金编号',
  `funds_name` varchar(200) DEFAULT NULL COMMENT '基金名称',
  `similarity` decimal(10,2) DEFAULT NULL COMMENT '推荐基金和行业的相似度',
  `data_date` varchar(10)  COMMENT '数据日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `industry_code_and_date` (`industry_code`,`data_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='各个板块的数据';