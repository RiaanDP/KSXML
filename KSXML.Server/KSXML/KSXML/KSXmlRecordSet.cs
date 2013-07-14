using System.Collections.Generic;
using System.Text;

namespace KSXML
{
    public class KSXmlRecordSet
    {
        private readonly List<KSXmlRecord> _recordList = new List<KSXmlRecord>();

        public void AddRecord(KSXmlRecord xmlRecord)
        {
            _recordList.Add(xmlRecord);
        }

        public string ToXml()
        {
            var sbuilder = new StringBuilder();

            sbuilder.Append("<ksxmlrecordset>");
            _recordList.ForEach(xmlRecord => sbuilder.Append(xmlRecord.ToXml()));
            sbuilder.Append("</ksxmlrecordset>");

            return sbuilder.ToString();
        }
    }
}
