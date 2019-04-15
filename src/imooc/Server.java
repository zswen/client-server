package imooc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//1. 创建服务器端Socket，即ServerSocket
			ServerSocket serverSocket = new ServerSocket(8888);
			System.out.println("****服务器即将启动****");
			//2. 调用 accept() 方法开始监听，等待客户端连接
			Socket socket = serverSocket.accept();
			//3. 获取输入流，并读取客户端信息
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr); // 给数据输入流添加缓冲
			String info = null;
			while ((info = br.readLine()) != null) {
				System.out.println("我是服务器，客户端说：" + info);
			}
			socket.shutdownInput(); // 关闭输入流
			//4. 获取输出流，响应客户端的请求
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("欢迎您");
			pw.flush(); // 调用flush()方法将缓冲的信息输出
			
			//5. 关闭资源
			pw.close();
			os.close();
			br.close();
			isr.close();
			is.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
