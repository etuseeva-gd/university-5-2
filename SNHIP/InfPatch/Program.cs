using System;
using System.IO;
using System.Text;

namespace InfPatch
{
    internal class Program
    {
        public static string PlaceWithHardDriveInfoPExe = "H\0a\0r\0d\0D\0r\0i\0v\0e\0P\0a\0t\0c\0h\0:\0";
        
        public static void Main(string[] args)
        {
            Console.WriteLine("Write path for file:");
            string path = Console.ReadLine();
            Update(path);
            Console.WriteLine("File updated!");
            
            Console.Read();
        }
        
        private static int FindPosition(string name)
        {
            using (StreamReader read = new StreamReader(File.Open(name, FileMode.Open, FileAccess.Read, FileShare.Read), Encoding.Default))
            {
                string row = read.ReadToEnd();
                return row.IndexOf(PlaceWithHardDriveInfoPExe);
            }
        }
        
        private static void Update(string file)
        {
            var peek = FindPosition(file);
            var masByte = GetRowByte("ok");

            using (var writer = new BinaryWriter(File.Open(file, FileMode.Open, FileAccess.Write), Encoding.Default))
            {
                writer.Seek(peek, SeekOrigin.Begin);
                writer.Write(masByte, 0, masByte.Length);
            }
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
    }
}