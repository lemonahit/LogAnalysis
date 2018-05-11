package com.imooc.log

import org.apache.spark.sql.{SaveMode, SparkSession}

/**
  * 使用Spark完成我们的数据清洗操作
  */
object SparkStatCleanJob {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("SparkStatCleanJob")
      .master("local[2]").getOrCreate()

    val accessRDD = spark.sparkContext.textFile("E:/项目资料/慕课网日志分析/项目资料/data/data/access.log")

    //accessRDD.take(10).foreach(println)

    //RDD ==> DF
    val accessDF = spark.createDataFrame(accessRDD.map(x => AccessConvertUtil.parseLog(x)),
      AccessConvertUtil.struct)

//    accessDF.printSchema()
//    accessDF.show(false)

    accessDF.coalesce(1).write.format("parquet").mode(SaveMode.Overwrite)
            .partitionBy("day").save("E:/项目资料/慕课网日志分析/项目资料/clean")

    spark.stop()
  }

}
