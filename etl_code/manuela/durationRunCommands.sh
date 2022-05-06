javac -classpath `yarn classpath` -d . DurationMapper.java
javac -classpath `yarn classpath` -d . DurationReducer.java
javac -classpath `yarn classpath`:. -d . Duration.java
jar -cvf duration.jar *.class
hadoop jar duration.jar Duration citibike_data/output/part-r-00000 /user/mr5340/citibike_data/duration_output
