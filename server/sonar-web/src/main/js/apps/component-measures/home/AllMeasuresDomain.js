/*
 * SonarQube
 * Copyright (C) 2009-2017 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
import React from 'react';
import PropTypes from 'prop-types';
import HomeMeasuresList from './HomeMeasuresList';
import { getLocalizedMetricDomain } from '../../../helpers/l10n';

export default function AllMeasuresDomain(props) {
  const { domain, component, displayHeader } = props;

  return (
    <li>
      {displayHeader &&
        <header className="page-header">
          <h3 className="page-title">
            {getLocalizedMetricDomain(domain.name)}
          </h3>
        </header>}

      <HomeMeasuresList domain={domain} component={component} />
    </li>
  );
}

AllMeasuresDomain.defaultProps = {
  displayHeader: true
};

AllMeasuresDomain.propTypes = {
  displayHeader: PropTypes.bool
};
