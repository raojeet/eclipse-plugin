package smartqa;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.json.JSONObject;
import org.eclipse.swt.widgets.MessageBox;

public class TestCasePanel {
    private Composite composite;
    private Text hostUrlInput, emailInput, passwordInput, pytestDirInput, pythonFileInput;
    private Label responseMessageLabel;
    private MainApp mainApp;

    public TestCasePanel(Composite parent, MainApp mainApp) {
        this.mainApp = mainApp;

        // Create a Composite as the container for the UI
        composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(2, false));

        // Host URL input
        Label hostUrlLabel = new Label(composite, SWT.NONE);
        hostUrlLabel.setText("Host URL:");
        hostUrlInput = new Text(composite, SWT.BORDER);
        hostUrlInput.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        hostUrlInput.setText("http://google.com");

        // Email input
        Label emailLabel = new Label(composite, SWT.NONE);
        emailLabel.setText("Email:");
        emailInput = new Text(composite, SWT.BORDER);
        emailInput.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        emailInput.setText("admin@example.com");

        // Password input
        Label passwordLabel = new Label(composite, SWT.NONE);
        passwordLabel.setText("Password:");
        passwordInput = new Text(composite, SWT.BORDER | SWT.PASSWORD);
        passwordInput.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        passwordInput.setText("123456");
        
        // Email input
        Label pytestDirLabel = new Label(composite, SWT.NONE);
        pytestDirLabel.setText("Pytest Directory:");
        pytestDirInput = new Text(composite, SWT.BORDER);
        pytestDirInput.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        pytestDirInput.setText("admin@example.com");
        

        // Python File input
        Label pythonFileLabel = new Label(composite, SWT.NONE);
        pythonFileLabel.setText("Python File:");
        pythonFileInput = new Text(composite, SWT.BORDER);
        pythonFileInput.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        // Buttons
        Button testCoverageBtn = new Button(composite, SWT.PUSH);
        testCoverageBtn.setText("Test Coverage");
        testCoverageBtn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

        Button testCommandBtn = new Button(composite, SWT.PUSH);
        testCommandBtn.setText("Test Command");
        testCommandBtn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

        Button clearAllBtn = new Button(composite, SWT.PUSH);
        clearAllBtn.setText("Clear All");
        clearAllBtn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

        // Ok button to trigger login
        Button okBtn = new Button(composite, SWT.PUSH);
        okBtn.setText("Login");
        okBtn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

        // Response message
        responseMessageLabel = new Label(composite, SWT.NONE);
        responseMessageLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        // Event Listener for Login Button
        testCoverageBtn.addListener(SWT.Selection, event -> handleTestCoverage());
        testCommandBtn.addListener(SWT.Selection, event -> handleTestCommand());
        clearAllBtn.addListener(SWT.Selection, event -> clearAllFields());
        okBtn.addListener(SWT.Selection, event -> handleSubmit());

    }

    public Composite getControl() {
        return composite;
    }
    
 // API Handlers
    private void handleTestCoverage() {
        String hostUrl = hostUrlInput.getText();
        responseMessageLabel.setText("Test coverage fetched from " + hostUrl);
        System.out.println("Fetching test coverage...");
    }

    private void handleTestCommand() {
        String hostUrl = hostUrlInput.getText();
        responseMessageLabel.setText("Test command executed at " + hostUrl);
        System.out.println("Running test command...");
    }

    private void clearAllFields() {
        hostUrlInput.setText("");
        emailInput.setText("");
        passwordInput.setText("");
        pytestDirInput.setText("");
        pythonFileInput.setText("");
        responseMessageLabel.setText("");
    }
    
    private void handleSubmit() {
        // Get form data
        String hostUrl = hostUrlInput.getText();
        String email = emailInput.getText();
        String password = passwordInput.getText();
        String pytestDir = pytestDirInput.getText();
        String pythonFile = pythonFileInput.getText();

        // Basic form validation
        if (hostUrl.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showErrorMessage("Please fill in all required fields.");
            return;
        }

        // Simulate API login
        new Thread(() -> {
            try {
//            	JSONObject response = ApiService.login(hostUrl, email, password, pytestDir, pythonFile);
            	
//            	String accessToken = response.getString("access_token");
                // Simulated API response
            	
                String accessToken = "mockedAccessToken";

                // On success, update UI and enable the Test Generator tab
                composite.getDisplay().asyncExec(() -> {
                    responseMessageLabel.setText("Login successful. Access Token: " + accessToken);
                    mainApp.enableTestGeneratorTab(accessToken);
                });
            } catch (Exception e) {
                composite.getDisplay().asyncExec(() -> showErrorMessage("Error: " + e.getMessage()));
            }
        }).start();
    }

    private void showErrorMessage(String message) {
        MessageBox messageBox = new MessageBox(composite.getShell(), SWT.ICON_ERROR | SWT.OK);
        messageBox.setMessage(message);
        messageBox.open();
    }
}
