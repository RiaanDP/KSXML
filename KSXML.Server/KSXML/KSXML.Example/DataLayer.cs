using System;
using System.Collections.Generic;
using System.Globalization;

namespace KSXML.Example
{
    public class DataLayer
    {
        private static DataLayer instance;

        public static DataLayer Instance
        {
            get { return instance ?? (instance = new DataLayer()); }
        }
        
        private readonly Dictionary<int, TrackInformation> _data = new Dictionary<int, TrackInformation>();

        public DataLayer()
        {
            _data.Add(1, new TrackInformation
                {
                    Name = "Futures",
                    Artist = "Jimmy Eat World",
                    Album = "Futures",
                    Length = 184
                });
            _data.Add(2, new TrackInformation
            {
                Name = "Panic Station",
                Artist = "Muse",
                Album = "The 2nd Law",
                Length = 218
            });
            _data.Add(3, new TrackInformation
            {
                Name = "Vertigo",
                Artist = "U2",
                Album = "How to Dismantle an Atomic Bomb",
                Length = 170
            });
        }

        public KSXmlRecord GetTrackInformation(int id)
        {
            if (!_data.ContainsKey(id))
            {
                throw new Exception("Invalid id");
            }

            var trackInformation = _data[id];

            var record = new KSXmlRecord();
            record.SetFieldValue("id", id);
            record.SetFieldValue("Name", trackInformation.Name);
            record.SetFieldValue("Artist", trackInformation.Artist);
            record.SetFieldValue("Album", trackInformation.Album);
            record.SetFieldValue("Length", trackInformation.Length.ToString(CultureInfo.InvariantCulture));

            return record;
        }

        public KSXmlRecordSet GetAllTrackNames()
        {
            var recordSet = new KSXmlRecordSet();

            foreach (var trackInformation in _data)
            {
                var record = new KSXmlRecord();
                record.SetFieldValue("id", trackInformation.Key);
                record.SetFieldValue("Name", trackInformation.Value.Name);
                recordSet.AddRecord(record);
            }

            return recordSet;
        }
    }
}