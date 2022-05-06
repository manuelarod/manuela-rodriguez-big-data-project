javac -classpath `yarn classpath` -d . CleanMapper.java
javac -classpath `yarn classpath` -d . CleanReducer.java
javac -classpath `yarn classpath`:. -d . Clean.java
jar -cvf clean.jar *.class
hadoop jar clean.jar Clean final_project/data/*.csv /user/mr5340/citibike_data/output
