using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Management;
using System.Text;

namespace InfFirstTask
{
    class Drive
    {
        public string Name;
        public string Model;
        public string FirmwareRevision;
        public string SerialNumber;
        public long Size;

        public override string ToString()
        {
            return Name + " " + Model + " " + FirmwareRevision + " " + Size;
        }
    }
    
    internal class Program
    {
        static readonly long ONE_GB = 1073741824;
        public static string PlaceWithHardDriveInfoExe = "H\0a\0r\0d\0D\0r\0i\0v\0e\0I\0n\0f\0o\0:\0";
        public static string PlaceWithHardDriveInfo = "HardDriveInfo:zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
        public static string PlaceWithHardDriveInfoP = "HardDrivePatch:zzzzzzzzzzzzzzzzzzz";
        
        public static void Main(string[] args)
        {   
            Drive drive = GetCurrentDrive(GetDrives());
            Console.WriteLine("Current hard drive: " + drive);

            if (FindPosition("InfFirstTask.exe") != -1)
            {
                RunExe();
                Item(drive);
                CreateBatFile();
            }
            else
            {
                if (isOK())
                {
                    Console.WriteLine("Application is working");
                }
                else
                {
                    Console.WriteLine(CheckHard(drive) ? "Application is working" : "Application can't work");
                }
                Console.Read(); 
            }
        }

        private static Drive GetCurrentDrive(List<Drive> drives)
        {
            string BaseDirectory = AppDomain.CurrentDomain.BaseDirectory;
            BaseDirectory = (BaseDirectory.Remove(BaseDirectory.IndexOf("\\")));
            
            foreach (var drive in drives)
            {
                if (BaseDirectory == drive.Name)
                {
                    return drive;
                }
            }
            return null;
        }

        private static List<Drive> GetDrives()
        {
            var drives = new List<Drive>();
            foreach (ManagementObject drive in
                new ManagementObjectSearcher("SELECT * FROM Win32_DiskDrive").Get())
            {
                var d = new Drive
                {
                    Model = (string) drive["Model"],
                    SerialNumber = (string) drive["SerialNumber"],
                    FirmwareRevision = (string) drive["FirmwareRevision"],
                    Size = long.Parse(drive["Size"].ToString()) / ONE_GB
                };

                foreach (ManagementObject partition in new ManagementObjectSearcher(
                    "ASSOCIATORS OF {Win32_DiskDrive.DeviceID='" + drive["DeviceID"]
                    + "'} WHERE AssocClass=Win32_DiskDriveToDiskPartition").Get())
                foreach (ManagementObject disk in new ManagementObjectSearcher(
                    "ASSOCIATORS OF {Win32_DiskPartition.DeviceID='" + partition["DeviceID"]
                    + "'} WHERE AssocClass=Win32_LogicalDiskToPartition").Get())
                    d.Name = (string) disk["Name"];
                drives.Add(d);
            }

            return drives;
        }
    
        private static int FindPosition(string name = "InfFirstTask2.exe")
        {
            using (StreamReader read = new StreamReader(File.Open(name, FileMode.Open, FileAccess.Read, FileShare.Read), Encoding.Default))
            {
                string row = read.ReadToEnd();
                return row.IndexOf(PlaceWithHardDriveInfoExe);
            }
        }
        
        private static bool isOK()
        {
            for (int i = PlaceWithHardDriveInfoP.Length - 1; i >= 0; i--)
            {
                if (PlaceWithHardDriveInfoP[i] == 'z')
                {
                    PlaceWithHardDriveInfoP = PlaceWithHardDriveInfoP.Remove(i);
                }
                else
                {
                    break;
                }
            }
            string ok = PlaceWithHardDriveInfoP.Substring(0, 2);
            return ok == "ok";
        }
        
        private static void CreateBatFile()
        {
            using (FileStream fstream = new FileStream(@"batFile.bat", FileMode.OpenOrCreate))
            {
                string[] words = new string[5];
                words[0] = "ping -n 1 -w 500 127.0.0.1 >nul" + Environment.NewLine;
                words[1] = "del InfFirstTask.exe /q /s" + Environment.NewLine;
                words[2] = "ren InfFirstTask2.exe InfFirstTask.exe" + Environment.NewLine;
                words[3] = "start InfFirstTask.exe" + Environment.NewLine;
                words[4] = "del %0";

                foreach (string item in words)
                {
                    byte[] array = System.Text.Encoding.Default.GetBytes(item);
                    fstream.Write(array, 0, array.Length);
                }
            }
            Process.Start(@"batFile.bat");
        }
        
        private static void Item(Drive drive)
        {
            var peek = FindPosition();
            
            var row = drive.Model + '@' + drive.SerialNumber + '@' + drive.FirmwareRevision + '@' + drive.Size;
            var masByte = GetRowByte(row);

            using (var writer = new BinaryWriter(File.Open("InfFirstTask2.exe", FileMode.Open, FileAccess.Write), Encoding.Default))
            {
                writer.Seek(peek, SeekOrigin.Begin);
                writer.Write(masByte, 0, masByte.Length);
            }
        }
        
        private static bool CheckHard(Drive drive)
        {
            for (int i = PlaceWithHardDriveInfo.Length - 1; i >= 0; i--)
            {
                if (PlaceWithHardDriveInfo[i] == 'z')
                {
                    PlaceWithHardDriveInfo = PlaceWithHardDriveInfo.Remove(i);
                }
                else
                {
                    break;
                }
            }
            string[] s = PlaceWithHardDriveInfo.Split(new char[] { '@' }, StringSplitOptions.RemoveEmptyEntries);
            
            Console.Write("File can be executed on: ");
            foreach (var s1 in s)
            {
                Console.Write(s1 + " ");
            }
            Console.WriteLine();
            
            return s[0] == drive.Model && s[1] == drive.SerialNumber && s[2] == drive.FirmwareRevision && s[3] == drive.Size + "";
        }
        
        private static byte[] GetRowByte(string row)
        {
            char[] masChar = row.Normalize().ToCharArray();
            byte[] masByte = new byte[masChar.Length * 2];
            int k = 0;
            for (int i = 0; i < masChar.Length; i++)
            {
                masByte[k++] = (byte)masChar[i];
                masByte[k++] = 0;
            }
            return masByte;
        }
        
        private static void RunExe()
        {
            if (File.Exists("InfFirstTask2.exe"))
                File.Delete("InfFirstTask2.exe");
            File.Copy("InfFirstTask.exe", "InfFirstTask2.exe");
        }
    }
}