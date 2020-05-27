import paramiko
import time


def findIp():
  ssh = paramiko.SSHClient()
  ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
  try:
    ssh.connect(hostname='192.168.1.2', username='root', password='Teamsvmware01!', port=3389)
  except Exception as error:
    print("Connection failed")

  try:
    stdin, stdout, stderr = ssh.exec_command("curl 145.44.234.18")
    command_result = stdout.read().decode('UTF8').strip("\n")
    if(command_result.find('192.168.0.5') != -1):
      print("Connected to webserver 192.168.0.5 via 145.44.234.18")
      ip = "192.168.0.5"
    else:
      print("Connected to webserver 192.168.0.6 via 145.44.234.18")
      ip = "192.168.0.6"
  except Exception as error:
    print("Test failed: command failed")
    print(error)
  ssh.close()
  return ip

def turnOff():
  ssh2 = paramiko.SSHClient()
  ssh2.set_missing_host_key_policy(paramiko.AutoAddPolicy())

  ip = findIp()
  try:
    ssh2.connect(hostname=ip, username='root', password='Teamsvmware01!', port=3389)
  except Exception as error:
    print("Connection failed")
    print(error)

  print("Turning off " + ip)

  try:
    stdin, stdout, stderr = ssh2.exec_command("systemctl reboot")
    time.sleep(4)
    print(ip + " turned off")
    findIp()
  except Exception as error:
    print("Test failed: command failed")
    print(error)
  ssh2.close()


print("------ Testing failover both servers are currently online ------")
turnOff()








