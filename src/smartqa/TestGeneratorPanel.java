package smartqa;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class TestGeneratorPanel {
    private Composite composite;
    private Text filePathInput;
    private Label messageLabel;
    private String accessToken;  // Store the access token

    public TestGeneratorPanel(Composite parent, String accessToken) {
        this.accessToken = accessToken;

        composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(2, false));

        // Model selection
        Label modelLabel = new Label(composite, SWT.NONE);
        modelLabel.setText("Choose Model:");
        String[] models = {"gpt-3", "gpt-4"};
        org.eclipse.swt.widgets.Combo modelSelect = new org.eclipse.swt.widgets.Combo(composite, SWT.READ_ONLY);
        modelSelect.setItems(models);
        modelSelect.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        // File input
        Label fileLabel = new Label(composite, SWT.NONE);
        fileLabel.setText("File Path:");
        filePathInput = new Text(composite, SWT.BORDER);
        filePathInput.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        // Generate button
        Button generateButton = new Button(composite, SWT.PUSH);
        generateButton.setText("Generate Test Cases");
        generateButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

        // Message label
        messageLabel = new Label(composite, SWT.NONE);
        messageLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        // Event listener for the button
        generateButton.addListener(SWT.Selection, event -> handleGenerateTestCases(
            modelSelect.getText(), filePathInput.getText()
        ));
    }

    public Composite getControl() {
        return composite;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    private void handleGenerateTestCases(String model, String filePath) {
        messageLabel.setText("Generating test cases for model: " + model + " with access token.");
        System.out.println("Model: " + model + ", File: " + filePath);
        System.out.println("Access Token: " + accessToken);
    }
}
