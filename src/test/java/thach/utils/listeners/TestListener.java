package thach.utils.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import thach.browsers.BaseSetup;
import thach.utils.helpers.CaptureHelpers;
import thach.utils.logs.Log;

public class TestListener implements ITestListener {

    @Override
    public void onFinish(ITestContext result) {
        Log.info("Kết thúc Auto test");
    }

    @Override
    public void onStart(ITestContext result) {
        Log.info("Đây là đoạn khởi động Auto test");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Log.info("Đây là testcase bị fail có phần trăm pass: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.error("Đây là test case bị fail: " + result.getName());
        //Chụp màn hình khi testcase bị fail:
        try {
            CaptureHelpers.captureScreenshot(BaseSetup.getDriver(), result.getName());
        } catch (Exception e) {
            Log.info("Exception while taking screenshot " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.info("Đây là test case bị bỏ qua: " + result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("Đây là test case chạy thành công: " + result.getName());
    }
}