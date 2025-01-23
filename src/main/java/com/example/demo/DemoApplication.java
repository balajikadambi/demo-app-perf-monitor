package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Cluster;
import com.example.demo.model.Environment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@SpringBootApplication
public class DemoApplication {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	@GetMapping(value = "/listclusters", produces = "application/json")
	public List<String> getAllClusters() {
		List<String> clusters = new ArrayList<String>();
    	clusters.add("<TABLE>" +
				"<TR><TH>Cluster ID</TH></TR>" +
				"<TR><TD>saas-k8s-local</TD></TR>" +
				"<TR><TD>artifactory-global</TD></TR>" +
				"<TR><TD>saas-k8s-global</TD></TR>" +
				"</TABLE>");
        return clusters;
	}

	@GetMapping(value = "/getpodsforcluster/{clusterid}", produces = "application/json")
	public List<String> getPodsForCluster(@PathVariable String clusterid) {
		List<String> pods = new ArrayList<String>();

		switch (clusterid) {
		case "saas-k8s-local":
			pods.add("<TABLE>" +
				"<TR><TH>Pod ID</TH></TR>" +
				"<TR><TD>log-processor</TD></TR>" +
				"<TR><TD>log-reader</TD></TR>" +
				"<TR><TD>log-writer</TD></TR>" +
				"</TABLE>");

			break;
		case "artifactory-global":
			pods.add("<TABLE>" +
				"<TR><TH>Pod ID</TH></TR>" +
				"<TR><TD>artifactory-aws</TD></TR>" +
				"<TR><TD>artifactory-nexus</TD></TR>" +
				"</TABLE>");

			break;
		case "saas-k8s-global":
			pods.add("<TABLE>" +
				"<TR><TH>Pod ID</TH></TR>" +
				"<TR><TD>app-data-reader</TD></TR>" +
				"<TR><TD>app-data-writer</TD></TR>" +
				"<TR><TD>app-data-processor</TD></TR>" +
				"<TR><TD>app-data-health-monitor</TD></TR>" +
				"</TABLE>");
			break;
		default:
			pods.add("No pods found for cluster-id : " + clusterid);
		}
		return pods;
	}

	@GetMapping(value = "/getlogsforpod/{podid}", produces = "application/json")
	public List<String> getLogsForCPod(@PathVariable String podid) {
		List<String> logs = new ArrayList<>();
		switch (podid) {
		case "log-processor":
		case "log-reader":
		case "log-writer":
			logs.add("<TABLE>" +
				"<TR><TH>Logs</TH></TR>" +
				"<TR><TD>1. 2025-01-13 09:07:05,733 ERROR log-reader c.i.l.r.h.DeleteLogsCommandHandler - Deleting logs for client saas_instana_test failed at 1735721760000000000: com.instana.clickhouse.client.exception.ClickHouseRequestException: Failed to execute ClickHouse request (root cause: DB::Exception Code: 159) Query: DELETE FROM logs.log_messages_v4 WHERE (client_id = 'saas_instana_test') AND (ts <= 1735721760000000000)</TD></TR>" +
				"<TR><TD>2. 2025-01-13 09:13:25,076 ERROR log-reader c.i.l.r.h.GetLogVolumeMetricsCommandHandler - Error in parsing volume metrics Invalid number of retention days: 6</TD></TR>" +
				"<TR><TD>3. 2025-01-13 09:13:25,075 WARN log-reader c.i.logging.shared.LogRetention - getRetentionFactor: Invalid retentionDays = 6</TD></TR>" +
				"<TR><TD>4. 2025-01-13 09:14:23,655 INFO log-reader c.i.l.r.h.DeleteLogsHistoryCommandHandler - Delete logs history called: client_id: \"saas_instana_test\"</TD></TR>" +
				"<TR><TD>5. [20241221 15:31:23.003094    12 HttpConnectionPool.cpp:78] Cannot post http://aggregator-0-1.aggregators-headless.instana-magenta-beeinstana.svc.cluster.local:9998/PutMetricData?desired_data_version=2&timestamp=1734794668828. Status: 0. Error Code: 1. Error: Failed to connect to aggregator-0-1.aggregators-headless.instana-magenta-beeinstana.svc.cluster.local port 9998 after 1 ms: Couldn't connect to server</TD></TR>" +
				"<TR><TD>6. [2024/12/21 15:31:23] [error] [output:cloudwatch_logs:cloudwatch_logs.0] Failed to create log stream</TD></TR>" +
				"<TR><TD>7. [2024/12/21 15:31:23] [error] [output:cloudwatch_logs:cloudwatch_logs.0] Failed to send events</TD></TR>" +
				"<TR><TD>8. 2024-12-21 15:31:00,449 WARN  appdata-writer c.i.a.writer.service.ChainsWriter - Failed to insert batch</TD></TR>" +
				"<TR><TD>9. 2024-12-21T15:31:15.386+00:00 | WARN  | stana-agent-scheduler-thread-6-4 | yDiscoveryTicker | com.instana.agent - 1.1.740 | Discovery time (20293 ms)</TD></TR>" +
				"<TR><TD>10. 2024-12-21 15:31:30,303 WARN  appdata-writer c.i.a.writer.service.CallsWriter - Failed to insert batch</TD></TR>" +
				"<TR><TD>11. 2024-12-21 15:31:44,424 WARN  instana-plg-issue-tracker c.i.b.c.s.PeriodicJobRegistryImpl - Periodic job Maintenance configs reloading has failed. Retrying in 60 sec.</TD></TR>" +
				"<TR><TD>12. 2024-12-21 15:32:04,129 WARN  log-writer c.i.l.w.service.LogMessageWriter - Failed to insert batch. Batch being reset.</TD></TR>" +
				"<TR><TD>13. I1221 15:31:38.830192 1 filter_out_schedulable.go:63] Filtering out schedulables</TD></TR>" +
				"<TR><TD>14. I1221 15:31:38.830211 1 filter_out_schedulable.go:120] 0 pods marked as unschedulable can be scheduled.</TD></TR>" +
				"<TR><TD>15. I1221 15:31:38.830219 1 filter_out_schedulable.go:83] No schedulable pods</TD></TR>" +
				"<TR><TD>16. I1221 15:31:38.830230 1 static_autoscaler.go:511] No unschedulable pods</TD></TR>" +
				"<TR><TD>17. I1221 15:31:38.632423 1 static_autoscaler.go:276] Starting main loop</TD></TR>" +
				"</TABLE>");
			break;

		case "artifactory-aws":
		case "artifactory-nexus":
			logs.add("<TABLE>" +
				"<TR><TH>Logs</TH></TR>" +
				"<TR><TD>1. 2025-01-13 09:07:05,733 ERROR log-reader c.i.l.r.h.DeleteLogsCommandHandler - Deleting logs for client saas_instana_test failed at 1735721760000000000: com.instana.clickhouse.client.exception.ClickHouseRequestException: Failed to execute ClickHouse request (root cause: DB::Exception Code: 159) Query: DELETE FROM logs.log_messages_v4 WHERE (client_id = 'saas_instana_test') AND (ts <= 1735721760000000000)</TD></TR>" +
				"<TR><TD>2. 2025-01-13 09:13:25,076 ERROR log-reader c.i.l.r.h.GetLogVolumeMetricsCommandHandler - Error in parsing volume metrics Invalid number of retention days: 6</TD></TR>" +
				"<TR><TD>3. 2025-01-13 09:13:25,075 WARN log-reader c.i.logging.shared.LogRetention - getRetentionFactor: Invalid retentionDays = 6</TD></TR>" +
				"<TR><TD>4. 2025-01-13 09:14:23,655 INFO log-reader c.i.l.r.h.DeleteLogsHistoryCommandHandler - Delete logs history called: client_id: \"saas_instana_test\"</TD></TR>" +
				"<TR><TD>5. [20241221 15:31:23.003094    12 HttpConnectionPool.cpp:78] Cannot post http://aggregator-0-1.aggregators-headless.instana-magenta-beeinstana.svc.cluster.local:9998/PutMetricData?desired_data_version=2&timestamp=1734794668828. Status: 0. Error Code: 1. Error: Failed to connect to aggregator-0-1.aggregators-headless.instana-magenta-beeinstana.svc.cluster.local port 9998 after 1 ms: Couldn't connect to server</TD></TR>" +
				"<TR><TD>6. . [2024/12/21 15:31:24] [error] [aws_credentials] Shared credentials file /root/.aws/credentials does not exist</TD></TR>" +
				"<TR><TD>7. 024-12-21 15:31:48,769 WARN SQL Error: 0, SQLState: 08S01</TD></TR>" +
				"<TR><TD>8. 024-12-21 15:31:48,769 WARN Instrument http.server.request.duration has exceeded the maximum allowed cardinality (1999).</TD></TR>" +
				"<TR><TD>9. 2024-12-21 15:32:02,138 WARN  instana-nightly-appdata-processor c.i.s.s.buffer.ReducedSpanBuffer - Duplicate span id 2a3941ed6d14412f</TD></TR>" +
				"<TR><TD>10. 2024-12-21 15:32:02,762 WARN  plgrelease-instana-processor c.i.g.c.a.ApplicationConfigsCache - Failed to retrieve application configs for {\"tenantName\"=\"plgrelease\",\"tenantUnitName\"=\"instana\"}</TD></TR>" +
				"<TR><TD>11. I1221 15:31:38.830372 1 eligibility.go:144] Node ip-10-10-64-127.us-west-2.compute.internal is not suitable for removal - cpu utilization too big (0.828194)</TD></TR>" +
				"<TR><TD>12. I1221 15:31:38.812418 1 aws_manager.go:186] Found multiple availability zones for ASG \"eks-k8s-infra-us-west-2-private-beeinstant-aggregator-arm-1-04c8749d-64f1-d65c-1b3d-c7cb73a757f3\"; using us-west-2a for failure-domain.beta.kubernetes.io/zone label</TD></TR>" +
				"</TABLE>");
			break;

		case "app-data-reader":
		case "app-data-writer":
		case "app-data-processor":
		case "app-data-health-monitor":
			logs.add("<TABLE>" +
				"<TR><TH>S No.</TH><TH>Logs</TH></TR>" +
				"<TR><TD>1. 2025-01-13 09:07:05,733 ERROR log-reader c.i.l.r.h.DeleteLogsCommandHandler - Deleting logs for client saas_instana_test failed at 1735721760000000000: com.instana.clickhouse.client.exception.ClickHouseRequestException: Failed to execute ClickHouse request (root cause: DB::Exception Code: 159) Query: DELETE FROM logs.log_messages_v4 WHERE (client_id = 'saas_instana_test') AND (ts <= 1735721760000000000)</TD></TR>" +
				"<TR><TD>2. 2025-01-13 09:13:25,076 ERROR log-reader c.i.l.r.h.GetLogVolumeMetricsCommandHandler - Error in parsing volume metrics Invalid number of retention days: 6</TD></TR>" +
				"<TR><TD>3. 2025-01-13 09:13:25,075 WARN log-reader c.i.logging.shared.LogRetention - getRetentionFactor: Invalid retentionDays = 6</TD></TR>" +
				"<TR><TD>4. 2025-01-13 09:14:23,655 INFO log-reader c.i.l.r.h.DeleteLogsHistoryCommandHandler - Delete logs history called: client_id: \"saas_instana_test\"</TD></TR>" +
				"<TR><TD>5. [2024/12/21 15:31:24] [error] An I/O error has occurred while writing a response message entity to the container output stream.</TD></TR>" +
				"<TR><TD>6. 2024-12-21 15:31:32,368 ERROR instana-plg-processor c.i.p.c.CustomHealthRuleRefreshService - Failed to refresh custom event specifications</TD></TR>" +
				"<TR><TD>7. 2024-12-21 15:32:03,327 WARN  instana-plg-issue-tracker c.i.g.client.GroundskeeperClient - Error while trying to fetch alert configurations</TD></TR>" +
				"<TR><TD>8. 2024-12-21 15:32:03,327 WARN  instana-plg-issue-tracker c.i.i.a.AlertConfigurationRegistry - Failed to reload alert configuration registry. Retrying in 30 sec.</TD></TR>" +
				"<TR><TD>9. 2024-12-21 15:32:04,283 WARN  instana-version-issue-tracker c.i.g.client.GroundskeeperClient - Not found while trying to fetch alert configurations</TD></TR>" +
				"<TR><TD>10. 024-12-21 15:31:48,769 WARN  instana-nightly-issue-tracker c.i.i.service.EventStateServiceImpl - The triggering issue 76R9kIZ5T4mppnlxn7SbFg has no matching triggered incident.</TD></TR>" +
				"<TR><TD>11. 024-12-21 15:31:48,769 WARN  instana-nightly-issue-tracker c.i.i.service.EventStateServiceImpl - The triggered incident Ry8ygC0WRv29pDOj-4ZU2Q has no matching triggering issue.</TD></TR>" +
				"<TR><TD>12. I1221 15:31:49.197846 1 static_autoscaler.go:541] Calculating unneeded nodes</TD></TR>" +
				"<TR><TD>13. 2024-12-21 15:31:52,205 INFO  instana-nightly-issue-tracker c.i.i.s.RootCauseAnalysisService - Attempting to get state for HQyrQ4VJTAqIzbHZRTrGGw - saas_instana_nightly</TD></TR>" +
				"<TR><TD>14. 2024-12-21 15:31:52,207 INFO  instana-nightly-issue-tracker c.i.i.s.RootCauseAnalysisService - Inference object set tags</TD></TR>" +
				"</TABLE>");
			break;

		default:
			logs.add("No logs found for pod-id : " + podid);
		}

		return logs;
	}

	@PostMapping(value = "/getinformationforlog", produces = "application/json")
	public List<String> getInformationForLog(@RequestParam("log") String log,
											 @RequestParam("projectId") String projectId,
											 @RequestParam("apikey") String apikey) {
		List<String> logInformation = new ArrayList<>();

		if(log == null || log.isEmpty())
		{
			logInformation.add("No information available for the log: " + log);
			return logInformation;
		}

		String baseUrl = "https://rca-analyzer-balajikibm-dev.apps.rm3.7wse.p1.openshiftapps.com/watsonx_ai_service";
		String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
				.queryParam("query", log)
				.queryParam("apikey", apikey)
				.queryParam("projectid", projectId)
				.toUriString();

		try
		{
			String result = restTemplate.getForObject(url, String.class);
			System.out.println(result);
            if(result != null) {
				result = result.trim().substring(1, result.length()-2);
				result = "<p>" + result + "</p>";
				result = result.replaceAll("Answer 0 =", "");
				result = result.replaceAll("\\\\n", "<br>");
				result = result.replaceAll("\\n", "<br>");
			}
			logInformation.add(result);
		}
		catch (Exception e)
		{
			logInformation.add("No information available for the log: " + log);
		}
		return logInformation;
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
