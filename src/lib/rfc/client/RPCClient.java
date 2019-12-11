package lib.rfc.client;

public class RPCClient {
    private String m_url;
    private boolean m_verbose;

    public RPCClient(String serveraddr, int port, IfSysinfo sysinfo) {
        this.m_url = new String("http://" + serveraddr + ":" + String.valueOf(port) + "/rpc");
        this.m_verbose = sysinfo.verbose;
    }

    public IfFeedback callAsync(String funcName, IfArgs funcArgs) {
        IfFeedback fb = new IfFeedback();

        return fb;
    }
}