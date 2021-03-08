# MySQL

#####　基本认识

```mysql
# 不区分大小写，推荐关键字大写
# 每条命令以 ; 结尾
# 注释
```

```mysql
# 单行注释
-- 单行注释
/*多行注释*/
```
##### 注意事项

```mysql
# '!=' 建议写为 '<>'
# SQL中所有集合索引从'1'开始
# 当查询字段名、别名会发生歧义时，字段名可用``括住，别名可用''、""括住
# 通常 ORDER BY 在查询语句尾部，LIMIT除外
```

##### 常用函数

```mysql
IFNULL(expr1,expr2) #判断expr1是否为'NULL'，如果是返回expr2，否则返回expr1
```

##### 字符函数

```mysql
LENGTH(expr1) #返回参数字节数
CONCAT(expr1,expr2,...) #连接任意个字符并返回
UPPER(str) #返回参数大写
LOWER(str) #返回参数小写
SUBSTR(str FROM pos [FOR len]) #返回str指定截取段，长度默认至尾部
INSTR(str,substr) #返回子串第一次出现的索引，若无返回0
TRIM([remstr FROM] str) #筛选指定字符默认空白
LPAD(str,len,padstr) #左填充指定字符至len长度，可能截断
REPLACE(str,from_str,to_str) #替换指定字符
```

##### 数学函数

```mysql
ROUND(X[,Y]) #四舍五入，Y为小数点后保留数
CEIL(X) #向上取整
FLOOR(X) #向下取整
TRUNCATE(X,D) #阶段，D为小数点后保留数
MOD(N,M) #取余，= N%M
```

##### 日期函数

```mysql
NOW() #返回当前日期+时间 2021-03-04 23:36:48
CURDATE() #返回当前日期 2021-03-04
CURTIME() #返回当前时间 23:37:54
YEAR(NOW()) #2021
MONTHNAME(NOW()) #March

STR_TO_DATE(str,format) #字符串->时间  
-- SELECT STR_TO_DATE('2021-01-01','%Y-%c-%d') //2021-01-01
DATE_FORMAT(date,format) #时间->字符串
-- SELECT DATE_FORMAT(NOW(),'%c-%D %Y') //3-5th 2021
/*
	%Y 4位年份；%y 2位年份；
	%m 2位月份；%c 1位月份；
	%d 日；
	%H 24小时制；%h 12小时制；
	%i 分钟；%s 秒
*/

DATEDIFF(expr1,expr2) #计算两个时间相差天数
-- SELECT DATEDIFF('2021-02-01','2021-01-01') //31
```

##### 聚合函数

```mysql
# 同聚合函数一起查询的字段，需要被 GROUP BY分组，否则无意义 
SUM(expr)
AVG([DISTINCT] expr)
COUNT(DISTINCT expr,[expr...])
MIN(expr)
MAX(expr)
```

* COUNT()

```mysql
Count(*) 统计表所有行数，即只要一条记录有一个字段非空/null则+1
Count(1) ：Sql为表新增一个字段，并赋值为1，然后进行计算
MYISAM存储引擎：Count(*)效率高，此引擎含有行计数器，Count(*)直接返回计数值
INNODB存储引擎：Count(1)于Count(*)差不多，但比Count('字符')稍高，因为后者多了一步字符判null步骤
```

##### 流程控制 

```mysql
#相当于三元运算符
IF(expr1,expr2,expr3) 

```

```mysql
# CASE函数，当有[变量|表达式|字段]相当于switch，没有时相当于多重if-else
# 作为独立语句(即CASE后没有变量|表达式|字段)时只能放在BEGIN END中
CASE [变量|表达式|字段]
	WHEN when_value THEN 值|变量
    WHEN when_value THEN 值|变量
	...
ELSE
		值|变量
END [CASE];
```

```mysql
# IF结构，只应用于BEGIN END中
IF 条件 THEN 语句;
ELSEIF 条件 THEN 语句;
ELSEIF 条件 THEN 语句;
[ELSE 语句;]
END IF;
```

```mysql
# ITERATE 相当于 CONTINUE；LEAVE 相当于 BREAKE

# 循环 WHILE
[标签:]WHILE 循环条件 DO
		循环体;
END WHILE[标签];

# 循环 LOOP
[标签:]LOOP
		循环体;
END LOOP[标签]

# 循环 REPEAT
[标签:]REPEAT
		循环体;
UNTIL 结束条件;
END REPEAT[标签];
```

## 变量

```mysql
# 系统变量前缀'@@'；用户变量前缀'@'；局部变量没有前缀；
# 全局变量对当前所有会话有效，但重启后恢复默认值；会话变量只对当前连接有效，且会受到全局变量影响；用户变量对当前连接有效；局部变量在BEGIN END中有效；
# 用户变量不需要指定类型，局部变量必须指定类型
```

##### 全局变量

```mysql
# 全局全量修改跨链接有效，但每次启动都会恢复默认值
# 如果要永久性修改，则需要修改MySQL安装目录下的配置文件
```

```mysql
SHOW GLOBAL VARIABLES; #查看全局变量
SHOW GLOBAL VARIABLES LIKE '%char%'; #查看部分全局变量
SELECT @@GLOBAL.autocommit; #查看指定全局变量
SET @@GLOBAL.autocommit=0; #修改全局变量值
```

##### 会话变量

``` mysql
# 不指定GLOBAL|SESSION，默认为SESSION
# 会话变量的修改只对当前连接有效
# 全局变量的修改会影响会话变量
```

```mysql
SHOW [SESSION] VARIABLES; #查看会话变量
SHOW [SESSION] VARIABLES LIKE '%char%'; #查看部分会话变量
SELECT @@[SESSION.]autocommit; #查看指定会话变量
SET @@[SESSION.]autocommit=0; #修改会话变量
```

##### 用户变量

```mysql
# 声明并赋值，必须初始化
SET @var = 1;
SET @var := 2;
SELECT @var :=2;
# 另一种赋值方式
SELECT 3 INTO @var;
```

##### 局部变量

```
# 声明
DECLARE var INT;
DECLARE var INT DEFAULT 1;
# 赋值
SET var = 2;
SET var := 3;
SELECT @var := 4;
SELECT 字段 INTO var FROM ...
```

## DQL

##### 去重查询

```mysql
# 去重查询
SELECT DISTINCT 
```
##### '+'作用

```mysql
SELECT '2'+1; #3，字符自动转换数值
SELECT 'john'+1; #1，字符转换数值失败默认值为0
SELECT null+1; #null，含null结果一定为null
```
##### 逻辑运算符

```mysql
/* 连接条件表达式 */
-- 与：&&  AND
-- 或：||  OR
-- 非：!   NOT
```

##### 模糊查询

```mysql
-- LIKE：常与通配符配合使用，%任意多个字符，_任意单个字符
-- LKKE实质是在做'=='判断，所以不能判断'NULL'
SELECT * FROM CITY WHERE `NAME` LIKE '%市';
```

```mysql
-- BETWEEN：包含临界值
-- AND
```

```mysql
-- IN：判断某个值是否属于某个集合，类型必须一致或兼容
-- ISNULL：'='不能判断'NULL'值
-- IS NOT NULL
```

##### 排序查询

```mysql
SELECT 查询对象 FROM 查询表 [WHERE 筛选条件] ORDER BY 比较对象 ASC|DESC
-- ASC(默认) 升序；DESC 降序
-- 比较对象必须含于查询对象，它可以为(多个)字段、别名、表达式、函数
```

##### 分组查询

```mysql
GROUP BY Fields(Excpte 聚合函数)
```

```mysql
# 分组后筛选，HAVING 并非一定要和 GROUP BY 连用 
GROUP BY Fields(Excpte 聚合函数) HAVING 筛选条件
```

```mysql
WHERE是分组前筛选
HAVING是分组后筛选
尽量分组前筛选所有数据，提高效率；但筛选需要使用聚合函数等的值时就只能在分组后进行筛选；
```

##### 连接查询

```mysql
/*
	MySQL支持内连接、左连接、右连接、交叉连接 
	连接条件关键字 ON 
*/
-- 		|----内连接 [INNER] JOIN
-- 				 |----等值连接
-- 				 |----非等值连接
-- 				 |----自连接
-- 		|----外连接
-- 				 |----左连接 LEFT JOIN
-- 				 |----右连接 RIGHT JOIN
-- 				 |----全外连接 FULL JOIN
-- 		|----交叉连接
```

##### 子查询

```mysql
# 子查询通常根据需求要满足查询结果 一列一行、一列多行、一行多列 
-- 		[NOT] IN 属于
-- 		ANY|SOME 任意
-- 		ALL 所有
```

##### 分页查询

```mysql
# 查询结果较大，需要分条目查询以节省资源，LIMIT语句在查询语句尾部 
[SELECT * FROM ...]
			LIMIT [OFFSET,]SIZE; #起始索引，条目个数，索引默认从0开始
```

##### 联合查询

```mysql
/* 
	将多条查询语句的结果联合为一个结果集 
	查询信息需要一致，联合的表没有联系		
	UNION默认去重，可以显式设置不去重 UNION ALL
*/
UNION
```

## DML

##### 表插入

```mysql
# 字段名省略默认插入所有字段值 
INSERT INTO 表名(FIELDS) VALUES (...)，VALUES (...)，... #插入指定值
INSERT INTO 表名(FIELDS) SELECT ... #插入查询语句结果
```

##### 表修改

```mysql
UPDATE 表名 [JOIN ...]
SET FIELDS = ( VALUES ) | ( SELECT...) 
WHERE
	...
```

##### 表删除

```mysql
DELETE FROM 表名 WHERE ... #行删除
DELETE A FROM A,B WHERE ... #表连接行删除
TRUNCATE TABLE 表名 #相当于clear()
```

```mysql
TRUNCATE不能回滚，DELETE可以回滚
TRUNCATE不能添加筛选条件
TRUNCATE没有返回值，DELETE有返回值
自增长列，DELETE删除后从断点开始，TRUNCATE从1开始
TRUNCATE效率较高
```

## DDL

##### 库

```mysql
# 库创建
CREATE DATABASE IF NOT EXISTS 库名
# 库字符集修改
ALTER DATABASE 库名 CHARSET SET utf8
# 库删除
DROP DATABASE IF EXISTS 库名
```

##### 表

```mysql
# 表创建
CREATE TABLE 表名 { 
		字段名 字段类型 [(长度) 约束 ]， 
		字段名 字段类型 [(长度) 约束 ]，
		...
		字段名 字段类型 [(长度) 约束 ] 
		}
# 表删除
DROP TABLE IF EXISTS 表名
```

```mysql
# 修改字段名
ALTER TABLE 表名 CHANGE [COLUMN] 字段名 新列名 新类型
# 修改字段 类型|列级约束
ALTER TABLE 表名 MODIFY COLUMN 字段名 类型|约束 
# 增加字段
ALTER TABLE 表名 ADD COLUMN 字段名 字段类型
# 删除字段
ALTER TABLE 表名 DROP COLUMN 字段名
# 修改表名
ALTER TABLE 表名 RENAME TO 新表名
```

```mysql
# 表复制
CREATE TABLE 新表 LIKE 旧表
		SELECT 字段 FROM 旧表
```

##### 类型

```mysql
/*
-- 		|----数值型
-- 				 |----整型：TINYINT、SMALLINT、MEDIUMINT、INT、INTEGER、BIGINT
-- 				 |----小数
--					  |----浮点型：FLOAT(M,D)、DOUBLE(M,D)
--					  |----定点型：DECIMAL(M,D)
-- 		|----字符型
-- 				 |----短文本：CHAR、VARCHAR
-- 				 |----长文本：TEXT、BLOB(二进制)
-- 		|----日期型：DATE、TIME、YEAR、DATETIME、TIMESTAMP(时间戳)
*/
```

```mysql
UNSIGNED 无符号关键字
DECIMAL(M,D) 使用需要指定精度，M默认为10，D默认为0
CHAR 默认为1
```

##### 约束

```mysql
#　约束就是一种限制，用于保证数据的可靠性和正确性
-- 		|----NOT NULL：非空约束
-- 		|----DEFAULT：默认约束
-- 		|----PRIMARY KEY：主键约束=非空+唯一
-- 		|----UNIQUE：唯一约束，可以为空
-- 		|----CHECK：检查约束,MySQL不支持
-- 		|----FOREIGN KEY：外键约束
```

```mysql
# 创建表时修改列级|表级约束
-- 列级约束直接在字段名后追加，不支持外键约束，但不报错
-- 表级约束在所有字段后追加，不支持非空、默认
[CONSTRAINT 索引名] 约束(字段名) #表级约束,不能添加非空,唯一
# 不指定索引名字，默认为字段名
# 修改表时增删表级约束
ALTER TABLE [ADD 约束名(字段名)]|[DROP 约束名 字段名] #表级
ALTER TABLE 表名 MODIFY COLUMN 字段名 类型|约束 #列级
```

* 创建表时设置约束

```mysql
DROP TABLE IF EXISTS stu_info
# 创建主表
CREATE TABLE IF NOT EXISTS stu_info(
        -- 列级约束直接在字段名后追加
        id INT, 
        name VARCHAR(20) NOT NULL, #非空
        seat INT UNIQUE, #唯一
        age INT DEFAULT 18, #默认
        majorID int,
        gender CHAR(1) CHECK(gender='male' OR gender='female'), #检查,MySQL不支持
		-- 表级约束在所有字段后追加
		[CONSTRAINT pk] PRIMARY KEY(id), #主键
		[CONSTRAINT fk] FOREIGN KEY(majorID) REFERENCES major(id) #外键
);
# 创建从表
CREATE TABLE IF NOT EXISTS major(
		id INT PRIMARY KEY, #主键
		`name` VARCHAR(20) UNIQUE #非空
);
```

* 修改表时增删约束

```mysql
# 列级约束修改，如果不填约束则恢复默认状态，相当于添加新字段，但保留值，不影响key
ALTER TABLE stu_info MODIFY COLUMN id INT NOT NULL; #增加非空约束
ALTER TABLE stu_info MODIFY COLUMN age INT DEFAULT 20; #修改默认值
# 表级约束修改
ALTER TABLE stu_info ADD UNIQUE(name); #增加唯一约束
ALTER TABLE stu_info ADD [CONSTRAINT pk] FOREIGN KEY(majorID) REFERENCES major(id); #增加外键约束
ALTER TABLE stu_info DROP INDEX name; #删除唯一约束

```

```mysql
# 主键约束与唯一约束
/*
	都具有字段值唯一约束能力
	主键约束不能非空,唯一约束可以有一个null
	主键约束一个表只能有一个,唯一约束可以有多个
	都支持组合约束
*/
```

```mysql
# 外键
/*
	要求从表中设置外键关系
	从表外键类型与主表关联外键类型一致或兼容,名称无要求
	被引用的外键必须是key(主键或唯一)
	插入数据,先插入主表,再插入从表
	删除数据,先删除从表,再删除主表
*/
```

##### 标识列

```mysql
# 即自增长列
# 标识列只能与key搭配(主键、唯一、外键)
# 自增长默认起始1，步长1，修改需要更改数据库设置
# 一张表至多一个标识列
# 标识列类型只能是数值型
# 标识列可以通过表列级修改增删
```

```mysql
DROP TABLE IF EXISTS major;
CREATE TABLE IF NOT EXISTS major(
		id INT PRIMARY KEY AUTO_INCREMENT, #主键
		`name` VARCHAR(20) UNIQUE #非空
)
```

## TCL

* Transaction Control Language 事务控制语言

* 事务由单元组成，每个单元中的SQL语句都是一个不可分割整体：即要么所有语句顺利执行，要么所有语句都不执行。当单元中有一条SQL执行失败，则整个单元都要回滚，操作的数据也会恢复初始状态。经典案例：银行转账

* 存储引擎：它决定了数据库中的数据使用了什么技术、规范进行存储，并不是所有引擎都支持事务，5.8目前的默认引擎InnoDB是支持事务的

##### ACID属性

* 原子性(Atomicity)：指事务是一个整体，不可分，即其中的SQL语句要全部(不)执行
* 一致性(Consistency)：事务执行前后数据库整体上处于一致状态
* 隔离性(Isolation)：多个事务的执行互不干扰，即并发安全同步问题
* 持久性(Durability)：事务一旦提交(执行)，对数据库的影响是永久的

 ##### 创建事务

```mysql
# 关闭自动提交事务，现在必须显式提交事务
SET autocommit = 0
# 开启事务
[START TRANSACTION]
# 编写事务内容
SQL语句1
SQL语句2
...
# 结束：提交事务/回滚事务
COMMIT / ROLLBACK
```

* 事务并发问题

```mysql
脏读：事务B读取事务A修改但未提交的数据，之后事务A进行回滚，事务B读取的是无效的数据
幻读：事务A插入|删除行，导致事务B在单元中两次访问表获得的数据不一致
不可重复读：事务A对某字段进行修改，导致事务B在单元中两次访问字段值不同
```

```mysql
# 每启动一个MySQL程序，就会获得一个单独的数据库连接，每个数据库连接都有一个全局变量 @@tx_isolation，表示当前事务隔离级别

SELECT @@tx_isolation; #查看当前事务隔离级别
SET SESSION TRANSACTION ISOLATION LEVEL (隔离级别); #设置当前连接事务隔离级别
SET GLOBAL TRANSACTION ISOLATION LEVEL (隔离级别);#设置数据库全局事务隔离级别

# MySQL 提供的4中隔离级别
READ UNCOMMITTED：最低隔离级别，所有安全问题都会出现
READ COMMITTED：只允许事务读取已被提交的变更，可以避免脏读
REPEATABLE READ：事务执行期间禁止其他事务对字段进行修改，可避免脏读、不可重复读
SERIALIZABLE：事务期间禁止其他事务对表进行插入、更新、删除，可避免所有并发问题，但性能十分低
```

##### 保存点

```mysql
SAVEPOINT 节点名
ROLLBACK TO 节点名
```

##### 两种删除

* DELETE 和 TRUNCATE 在事务中区别：DELETE可以回滚，TRUNCATE不能回滚

## 视图

* 视图是虚拟的表，它的数据全部来自于定义视图时查询的表
* 视图只保存逻辑，没有真实数据，使用时会动态生成临时表
* 视图通常设置权限为只读

##### 视图作用

```mysql
# 可以简化复杂SQL使用
# 重用SQL
# 提高数据安全性，视图将原表中可暴露的数据提供给客户端
```

#####　视图操作

```mysql
# 视图创建，可选择替换同名视图
CREATE [OR REPLACE] VIEW 视图名
AS
	SELECT语句;

# 视图修改
ALTER VIEW 视图名
AS
	SELECT语句;
# 删除视图
DROP VIEM 视图名

# 只有视图的数据能直接从原始表框出来时，才能修改
# 增，
INSERT INTO ...
# 改，相当于更新原始表
UPDATE 视图名 ...
# 删，相当于修改原始表
DELETE FROM 视图名 ...
```

## 存储过程

```mysql
# PROCEDURE参数有IN、OUT、INOUT三种模式，IN参数必须有输入值，OUT参数不需要输入值，它只用于接收PROCEDURE的返回值
# PROCEDURE可以没有返回值，也可以有多个返回值
```

##### 创建

```mysql
# 输入时间，返回时间格式化字符串
CREATE PROCEDURE date2str(IN time DATETIME,OUT str VARCHAR(20))
BEGIN
		SELECT DATE_FORMAT(time,'%Y/%m/%d') INTO str;
END

CALL date2str('2021-01-01', @str);
SELECT @str;
```

##### 删除

```mysql
DROP PROCEDURE IF EXISTS date2str; #PROCEDURE的删除
```

##### 查看

```mysql
SHOW CREATE PROCEDURE date2str; # 查看PROCEDURE
```

## 函数

```mysql
# 函数有且仅有1个返回值
# 函数参数没有模式
```

##### 创建

```mysql
DROP FUNCTION IF EXISTS my_sum;
CREATE FUNCTION my_sum(a FLOAT,b FLOAT) RETURNS FLOAT
BEGIN
		DECLARE sum FLOAT; #局部变量
		SET sum = a+b;
		RETURN sum;
END
SELECT my_sum(20.0,10); #30
```

##### 删除

```mysql
DROP FUNCTION IF EXISTS my_sum; #FUNCTION删除
```

##### 查看

```mysql
SHOW CREATE FUNCTION MY_SUM; #FUNCTION查看
```

