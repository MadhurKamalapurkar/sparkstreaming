<%@page import="com.daksh.project.factory.MongoObjectFactory"%>
<%@page import="com.daksh.project.util.DBObjectUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Random"%>
<%@page import="com.daksh.utility.DeviceStats"%>
<%@page import="com.daksh.utility.MongoDBUtil"%>
<%@page import="com.daksh.utility.DBUtil"%>
<%

// DeviceStats deviceStats = new DeviceStats();
// Random random = new Random();
// deviceStats.setAverageDeapth(random.nextInt(100000));
// deviceStats.setAverageLatency(random.nextInt(50000));
// deviceStats.setLatency(random.nextInt(100));
// deviceStats.setMaxDeapth(random.nextInt(20000));
// deviceStats.setRx_pckt_lost(random.nextInt(200));
// deviceStats.setRx_total_pckts(random.nextInt(400));
// deviceStats.setTx_pckt_lost(random.nextInt(600));
// deviceStats.setTx_total_pckts(random.nextInt(100));
// deviceStats.setQueueDeapth(random.nextInt(400));
// deviceStats.setLastTimeStamp(new Date().getTime());

// out.println(new Gson().toJson(deviceStats));


String device = request.getParameter("deviceName");//"analytics-qfx5100-01";
DBObjectUtil<DeviceStats> util = new MongoObjectFactory<DeviceStats>()
.getDBObjectUtil("MONGODB");



out.println((util.readDeviceLevelData(device)));
%>