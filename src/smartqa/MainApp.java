package smartqa;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainApp {
    private Shell shell;
    private CTabFolder tabFolder;
    private TestCasePanel testCasePanel;
    private TestGeneratorPanel testGeneratorPanel;
    private boolean isLoggedIn = false;  // Track if login is successful

    public MainApp(Shell parentShell) {
        // Use the provided Shell instead of creating a new one
        shell = parentShell;
        shell.setText("Smart QA");
        shell.setLayout(new FillLayout());

        // Create the tab folder
        tabFolder = new CTabFolder(shell, SWT.BORDER);
        tabFolder.setSimple(false);

        // Create TestCasePanel Tab
        testCasePanel = new TestCasePanel(tabFolder, this);
        CTabItem testCaseTab = new CTabItem(tabFolder, SWT.NONE);
        testCaseTab.setText("Test Case");
        testCaseTab.setControl(testCasePanel.getControl());

        // Create TestGeneratorPanel Tab but simulate it being "disabled" initially
        testGeneratorPanel = new TestGeneratorPanel(tabFolder, null); // Access token is null initially
        CTabItem testGenTab = new CTabItem(tabFolder, SWT.NONE);
        testGenTab.setText("Test Generator");
        testGenTab.setControl(testGeneratorPanel.getControl());

        // Add a listener to prevent selection of the "disabled" tab
        tabFolder.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent event) {
                if (tabFolder.getSelectionIndex() == 1 && !isLoggedIn) {
                    // If the user tries to select the second tab (index 1) and is not logged in, prevent it
                    event.doit = false;
                    tabFolder.setSelection(0);  // Keep the selection on the first tab
                }
            }
        });

        // Open the shell and set its size
        shell.setSize(600, 500);
        shell.open();
    }

    // This method is called when login is successful
    public void enableTestGeneratorTab(String accessToken) {
        isLoggedIn = true;  // Mark login as successful
        testGeneratorPanel.setAccessToken(accessToken); // Set the access token
        tabFolder.setSelection(1);  // Automatically switch to the Test Generator tab
    }
}
