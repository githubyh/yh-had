日志数据分析：
1.背景
1.1 黑马论坛日志，数据分为两部分组成，原来是一个大文件，是56GB；以后每天生成一个文件，大约是150-200MB之间；
1.2 日志格式是apache common日志格式；
1.3 分析一些核心指标，供运营决策者使用；
1.4 开发该系统的目的是分了获取一些业务相关的指标，这些指标在第三方工具中无法获得的；

2.开发步骤
2.1 把日志数据上传到HDFS中进行处理
    如果是日志服务器数据较小、压力较小，可以直接使用shell命令把数据上传到HDFS中；
	如果是日志服务器数据较大、压力较答，使用NFS在另一台服务器上上传数据；
	如果日志服务器非常多、数据量大，使用flume进行数据处理；
2.2 使用MapReduce对HDFS中的原始数据进行清洗；
2.3 使用Hive对清洗后的数据进行统计分析；
2.4 使用Sqoop把Hive产生的统计结果导出到mysql中；
2.5 如果用户需要查看详细数据的话，可以使用HBase进行展现；

3.详细代码
3.1 使用shell命令把数据从linux磁盘上传到HDFS中
3.1.1 在hdfs中创建目录，命令如下
    $HADOOP_HOME/bin/hadoop fs -mkdir /hmbbs_logs
3.1.2 写一个shell脚本，叫做upload_to_hdfs.sh，内容大体如下
    yesterday=`date --date='1 days ago' +%Y_%m_%d`
    hadoop fs -put /apache_logs/access_${yesterday}.log   /hmbbs_logs
3.1.3 把脚本upload_to_hdfs.sh配置到crontab中，执行命令crontab -e, 写法如下
    * 1 * * * upload_to_hdfs.sh

	3.2 使用MapReduce对数据进行清洗，把原始处理清洗后，放到hdfs的/hmbbs_cleaned目录下，每天产生一个子目录。	

3.3 使用hive对清洗后的数据进行统计。
3.3.1 建立一个外部分区表，脚本如下
    CREATE EXTERNAL TABLE hmbbs(ip string, atime string, url string) PARTITIONED BY (logdate string) ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' LOCATION '/hmbbs_cleaned';
3.3.2 增加分区，脚本如下
	ALTER TABLE hmbbs ADD PARTITION(logdate='2013_05_30') LOCATION '/hmbbs_cleaned/2013_05_30';
	把代码增加到upload_to_hdfs.sh中，内容如下
	hive -e "ALTER TABLE hmbbs ADD PARTITION(logdate='${yesterday}') LOCATION '/hmbbs_cleaned/${yesterday}';"
3.3.3 统计每日的pv，代码如下
      CREATE TABLE hmbbs_pv_2013_05_30 AS SELECT COUNT(1) AS PV FROM hmbbs WHERE logdate='2013_05_30';
	  统计每日的注册用户数，代码如下
	  CREATE TABLE hmbbs_reguser_2013_05_30 AS SELECT COUNT(1) AS REGUSER FROM hmbbs WHERE logdate='2013_05_30' AND INSTR(url,'member.php?mod=register')>0;
	  统计每日的独立ip，代码如下
      CREATE TABLE hmbbs_ip_2013_05_30 AS SELECT COUNT(DISTINCT ip) AS IP FROM hmbbs WHERE logdate='2013_05_30';
	  统计每日的跳出用户，代码如下
	  CREATE TABLE hmbbs_jumper_2013_05_30 AS SELECT COUNT(1) AS jumper FROM (SELECT COUNT(ip) AS times FROM hmbbs WHERE logdate='2013_05_30' GROUP BY ip HAVING times=1) e;
	  把每天统计的数据放入一张表 
	  CREATE TABLE hmbbs_2013_05_30 AS SELECT '2013_05_30', a.pv, b.reguser, c.ip, d.jumper FROM hmbbs_pv_2013_05_30 a JOIN hmbbs_reguser_2013_05_30 b ON 1=1 JOIN hmbbs_ip_2013_05_30 c ON 1=1 JOIN hmbbs_jumper_2013_05_30 d ON 1=1 ;
3.4 使用sqoop把数据导出到mysql中	  