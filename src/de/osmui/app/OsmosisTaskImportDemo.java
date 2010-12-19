package app;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import model.osm.OsmosisTaskDescription;
import model.osm.TParameter;
import model.osm.TTask;
import model.osm.TTaskGroup;

/**
 * Einfache Demo: Dynamisches Einlesen der Osmosis-Taskdefinitionen (d.h. der
 * verfügbaren Tasks mit Parametern, zulässigen Werten, Standardwerten etc.) zur
 * Laufzeit aus einer XML-Datei.
 * 
 * @author roederhr
 * @version 0.1
 * 
 */

public class OsmosisTaskImportDemo {

	public static void main(String[] args) {

		try {
			/*
			 * JAXBContext holen (unter Angabe des Pakets, in dem die
			 * generierten Klassen liegen)
			 */
			JAXBContext jc;
			jc = JAXBContext.newInstance("model.osm");

			/* XML-Datei mit Osmosis-Task-Beschreibungen einlesen */
			File xmlTasksFile = new File("osmosis-tasks.xml"); //
			Unmarshaller u = jc.createUnmarshaller();
			OsmosisTaskDescription otd = (OsmosisTaskDescription) u
					.unmarshal(xmlTasksFile);

			Map<String, TTask> tasks = new HashMap<String, TTask>();

			/* Beispiel 1: Alle Tasks auflisten */
			for (TTaskGroup group : otd.getTaskGroup()) {
				System.out.println(group.getFriendlyName() + ":");
				for (TTask task : group.getTask()) {
					tasks.put(task.getName(), task);
                    System.out.println("---- "+task+" ----");
					System.out.println("   " + task.getName());
				}
			}



			/*
			 * Beispiel 2: Zulässige Parameter für den Task '--read-apidb'
			 * bestimmen
			 */
			TTask taskReadApiDb = tasks.get("bounding-box");
			if (taskReadApiDb != null) {
				System.out.println("\n\nParameter für Task "
						+ taskReadApiDb.getName()
						+ " (in Klammern: Standardwert)");
				for (TParameter parameter : taskReadApiDb.getParameter()) {
					System.out.print("   " + parameter.getName() + ": "
							+ parameter.getType());
					System.out
							.println(" (" + parameter.getDefaultValue() + ")");

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
