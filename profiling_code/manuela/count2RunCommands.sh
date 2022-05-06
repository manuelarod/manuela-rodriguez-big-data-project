javac -classpath `yarn classpath` -d . CountRecsMapper.java
javac -classpath `yarn classpath` -d . CountRecsReducer.java
javac -classpath `yarn classpath`:. -d . CountRecs.java
jar -cvf countRecs.jar *.class
hadoop jar countRecs.jar CountRecs citibike_data/output/part-r-00000 /user/mr5340/citibike_data/count2_output
